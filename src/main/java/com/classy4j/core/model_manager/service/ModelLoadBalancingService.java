package com.classy4j.core.model_manager.service;

import lombok.Data;

@Data
public class ModelLoadBalancingService {
    private boolean enabled;
    private String strategy;
    private String config;
    
    /**
     * Enable load balancing with specified strategy and configuration
     * 
     * @param strategy The load balancing strategy to use
     * @param config The configuration for the strategy
     */
    public void enableLoadBalancing(String strategy, String config) {
        this.enabled = true;
        this.strategy = strategy;
        this.config = config;
    }
    
    /**
     * Disable load balancing
     */
    public void disableLoadBalancing() {
        this.enabled = false;
        this.strategy = null;
        this.config = null;
    }
    
    /**
     * Update load balancing configuration
     * 
     * @param config The new configuration
     */
    public void updateLoadBalancingConfig(String config) {
        if (this.enabled) {
            this.config = config;
        }
    }
    
    /**
     * Check if load balancing is enabled
     * 
     * @return true if load balancing is enabled, false otherwise
     */
    public boolean isLoadBalancingEnabled() {
        return this.enabled;
    }
    
    /**
     * Get current load balancing strategy
     * 
     * @return The current strategy or null if disabled
     */
    public String getCurrentStrategy() {
        return this.strategy;
    }
    
    /**
     * Get current load balancing configuration
     * 
     * @return The current configuration or null if disabled
     */
    public String getCurrentConfig() {
        return this.config;
    }
}