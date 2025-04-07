package com.classy4j.core.model_manager.service;

import java.util.List;
import java.util.Map;

import com.classy4j.core.model_manager.model.ProviderEntity;
import com.classy4j.core.model_manager.model.ProviderModelBundle;

import lombok.Data;

@Data
public class ModelProviderService {
    private Map<String, ProviderEntity> providers;
    private Map<String, ProviderModelBundle> modelBundles;
    
    /**
     * Get all available providers
     * 
     * @return List of provider entities
     */
    public List<ProviderEntity> getProviders() {
        return List.copyOf(providers.values());
    }
    
    /**
     * Get provider by name
     * 
     * @param providerName The name of the provider
     * @return The provider entity or null if not found
     */
    public ProviderEntity getProvider(String providerName) {
        return providers.get(providerName);
    }
    
    /**
     * Get model bundles for a provider
     * 
     * @param providerName The name of the provider
     * @return List of model bundles
     */
    public List<ProviderModelBundle> getProviderModelBundles(String providerName) {
        return modelBundles.values().stream()
                .filter(bundle -> bundle.getProviderName().equals(providerName))
                .toList();
    }
    
    /**
     * Validate provider credentials
     * 
     * @param providerName The name of the provider
     * @param credentials The credentials to validate
     * @return true if credentials are valid, false otherwise
     */
    public boolean validateProviderCredentials(String providerName, Map<String, String> credentials) {
        ProviderEntity provider = getProvider(providerName);
        if (provider == null) {
            return false;
        }
        // TODO: Implement actual credential validation logic
        return true;
    }
    
    /**
     * Update provider credentials
     * 
     * @param providerName The name of the provider
     * @param credentials The new credentials
     */
    public void updateProviderCredentials(String providerName, Map<String, String> credentials) {
        ProviderEntity provider = getProvider(providerName);
        if (provider != null) {
            provider.setGlobalCredentials(credentials.toString());
        }
    }
    
    /**
     * Update model credentials for a provider
     * 
     * @param providerName The name of the provider
     * @param modelName The name of the model
     * @param credentials The new credentials
     */
    public void updateModelCredentials(String providerName, String modelName, Map<String, String> credentials) {
        ProviderModelBundle bundle = modelBundles.values().stream()
                .filter(b -> b.getProviderName().equals(providerName) && b.getModel().equals(modelName))
                .findFirst()
                .orElse(null);
        
        if (bundle != null) {
            bundle.setModelCredentials(credentials.toString());
        }
    }
}