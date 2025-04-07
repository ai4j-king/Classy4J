package com.classy4j.core.model_manager.service;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class FeatureService {
    private Map<String, Map<String, Object>> tenantFeatures;
    private Map<String, Object> systemFeatures;
    
    public FeatureService() {
        this.tenantFeatures = new HashMap<>();
        this.systemFeatures = new HashMap<>();
    }
    
    /**
     * Get tenant features
     * 
     * @param tenantId The tenant ID
     * @return Map of features for the tenant
     */
    public Map<String, Object> getTenantFeatures(String tenantId) {
        return tenantFeatures.getOrDefault(tenantId, new HashMap<>());
    }
    
    /**
     * Update tenant features
     * 
     * @param tenantId The tenant ID
     * @param features The features to update
     */
    public void updateTenantFeatures(String tenantId, Map<String, Object> features) {
        tenantFeatures.put(tenantId, features);
    }
    
    /**
     * Get system features
     * 
     * @return Map of system features
     */
    public Map<String, Object> getSystemFeatures() {
        return new HashMap<>(systemFeatures);
    }
    
    /**
     * Update system features
     * 
     * @param features The features to update
     */
    public void updateSystemFeatures(Map<String, Object> features) {
        systemFeatures.putAll(features);
    }
    
    /**
     * Check if a feature is enabled for a tenant
     * 
     * @param tenantId The tenant ID
     * @param featureKey The feature key to check
     * @return true if the feature is enabled, false otherwise
     */
    public boolean isTenantFeatureEnabled(String tenantId, String featureKey) {
        Map<String, Object> features = getTenantFeatures(tenantId);
        return features.containsKey(featureKey) && 
               features.get(featureKey) instanceof Boolean && 
               (Boolean) features.get(featureKey);
    }
    
    /**
     * Check if a system feature is enabled
     * 
     * @param featureKey The feature key to check
     * @return true if the feature is enabled, false otherwise
     */
    public boolean isSystemFeatureEnabled(String featureKey) {
        return systemFeatures.containsKey(featureKey) && 
               systemFeatures.get(featureKey) instanceof Boolean && 
               (Boolean) systemFeatures.get(featureKey);
    }
}