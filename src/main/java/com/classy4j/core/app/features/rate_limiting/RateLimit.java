package com.classy4j.core.app.features.rate_limiting;

import com.classy4j.exception.AppInvokeQuotaExceededError;
import com.classy4j.exception.RateLimitError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 请求限制器
 */
public class RateLimit {
    private static final Logger logger = LoggerFactory.getLogger(RateLimit.class);
    
    private static final String MAX_ACTIVE_REQUESTS_KEY = "dify:rate_limit:%s:max_active_requests";
    private static final String ACTIVE_REQUESTS_KEY = "dify:rate_limit:%s:active_requests";
    private static final String UNLIMITED_REQUEST_ID = "unlimited_request_id";
    private static final int REQUEST_MAX_ALIVE_TIME = 10 * 60; // 10 minutes
    private static final int ACTIVE_REQUESTS_COUNT_FLUSH_INTERVAL = 5 * 60; // recalculate request_count from request_detail every 5 minutes
    
    private static final Map<String, RateLimit> instanceDict = new HashMap<>();
    private static final JedisPool jedisPool = new JedisPool();
    
    private final String clientId;
    private final String activeRequestsKey;
    private final String maxActiveRequestsKey;
    private int maxActiveRequests;
    private long lastRecalculateTime;
    private boolean initialized;
    
    private RateLimit(String clientId, int maxActiveRequests) {
        this.clientId = clientId;
        this.maxActiveRequests = maxActiveRequests;
        this.activeRequestsKey = String.format(ACTIVE_REQUESTS_KEY, clientId);
        this.maxActiveRequestsKey = String.format(MAX_ACTIVE_REQUESTS_KEY, clientId);
        this.lastRecalculateTime = Long.MIN_VALUE;
        this.initialized = false;
    }
    
    public static RateLimit getInstance(String clientId, int maxActiveRequests) {
        return instanceDict.computeIfAbsent(clientId, k -> new RateLimit(k, maxActiveRequests));
    }
    
    public void flushCache(boolean useLocalValue) {
        lastRecalculateTime = System.currentTimeMillis() / 1000;
        
        try (Jedis jedis = jedisPool.getResource()) {
            // flush max active requests
            if (useLocalValue || !jedis.exists(maxActiveRequestsKey)) {
                Pipeline pipe = jedis.pipelined();
                pipe.set(maxActiveRequestsKey, String.valueOf(maxActiveRequests));
                pipe.expire(maxActiveRequestsKey, Duration.of(1, ChronoUnit.DAYS).toSeconds());
                pipe.sync();
            } else {
                maxActiveRequests = Integer.parseInt(jedis.get(maxActiveRequestsKey));
                jedis.expire(maxActiveRequestsKey, Duration.of(1, ChronoUnit.DAYS).toSeconds());
            }
            
            // flush active requests (in-transit request list)
            if (!jedis.exists(activeRequestsKey)) {
                return;
            }
            
            Map<String, String> requestDetails = jedis.hgetAll(activeRequestsKey);
            jedis.expire(activeRequestsKey, Duration.of(1, ChronoUnit.DAYS).toSeconds());
            
            long currentTime = System.currentTimeMillis() / 1000;
            requestDetails.entrySet().stream()
                .filter(entry -> currentTime - Double.parseDouble(entry.getValue()) > REQUEST_MAX_ALIVE_TIME)
                .map(Map.Entry::getKey)
                .forEach(key -> jedis.hdel(activeRequestsKey, key));
        }
    }
    
    public String enter(String requestId) {
        if (System.currentTimeMillis() / 1000 - lastRecalculateTime > ACTIVE_REQUESTS_COUNT_FLUSH_INTERVAL) {
            flushCache(false);
        }
        
        if (maxActiveRequests <= 0) {
            return UNLIMITED_REQUEST_ID;
        }
        
        if (requestId == null) {
            requestId = genRequestKey();
        }
        
        try (Jedis jedis = jedisPool.getResource()) {
            long activeRequestsCount = jedis.hlen(activeRequestsKey);
            if (activeRequestsCount >= maxActiveRequests) {
                throw new AppInvokeQuotaExceededError(
                    String.format("Too many requests. Please try again later. The current maximum concurrent requests allowed is %d.",
                        maxActiveRequests));
            }
            
            jedis.hset(activeRequestsKey, requestId, String.valueOf(System.currentTimeMillis() / 1000));
        }
        
        return requestId;
    }
    
    public void exit(String requestId) {
        if (UNLIMITED_REQUEST_ID.equals(requestId)) {
            return;
        }
        
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hdel(activeRequestsKey, requestId);
        }
    }
    
    public static String genRequestKey() {
        return UUID.randomUUID().toString();
    }
    
    public <T> T generate(T generator, String requestId) {
        return generator;
    }
}