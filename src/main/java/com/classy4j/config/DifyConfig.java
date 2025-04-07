package com.classy4j.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "dify")
public class DifyConfig {
    private String apiKey;
    private String apiEndpoint;
    private String defaultProvider;
    private String defaultModel;
    private Integer maxRetries = 3;
    private Long requestTimeout = 30000L;
    private Boolean enableCache = true;
    private String cacheExpiration = "1h";
    private Boolean enableRateLimit = true;
    private Integer rateLimit = 100;
    private String rateLimitPeriod = "1m";
}