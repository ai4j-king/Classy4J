package com.classy4j.core.provider_manager;

import com.classy4j.helper.EncryptHelper;
import com.classy4j.model.*;
import com.classy4j.helper.ProviderCredentialsCache;
import com.classy4j.config.DifyConfig;


import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ProviderManager {

    @Autowired
    private EncryptHelper encryptHelper;
    
    @Autowired
    private ProviderCredentialsCache providerCredentialsCache;
    
    @Autowired
    private DifyConfig difyConfig;
    
    /**
     * Get model provider configurations.
     *
     * @param tenantId workspace id
     * @return provider configurations
     */
    public ProviderConfigurations getConfigurations(String tenantId) {
        return null;
    }
    

}