package com.classy4j.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class ProviderCredentialsCache {
    private static final String CACHE_NAME = "provider_credentials";

    @Autowired
    private EncryptHelper encryptHelper;

    @Cacheable(value = CACHE_NAME, key = "#tenantId + '_' + #providerName")
    public String getCredentials(String tenantId, String providerName, String encryptedCredentials, String secretKey) {
        return encryptHelper.decrypt(encryptedCredentials, secretKey);
    }

    @CacheEvict(value = CACHE_NAME, key = "#tenantId + '_' + #providerName")
    public void clearCredentials(String tenantId, String providerName) {
        // Cache entry will be automatically removed
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public void clearAllCredentials() {
        // All cache entries will be removed
    }
}