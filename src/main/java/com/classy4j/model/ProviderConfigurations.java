package com.classy4j.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.classy4j.core.model_manager.model.ModelEntity;
import com.classy4j.core.model_manager.model.ModelType;
import lombok.Data;

@Data
public class ProviderConfigurations {
    private String tenantId;
    private Map<String, ProviderConfiguration> configurations;
    
    public ProviderConfigurations(String tenantId) {
        this.tenantId = tenantId;
        this.configurations = new HashMap<>();
    }
    
    public void put(String provider, ProviderConfiguration configuration) {
        configurations.put(provider, configuration);
    }
    
    public ProviderConfiguration get(String provider) {
        return configurations.get(provider);
    }
    
    public List<ModelEntity> getModels(ModelType modelType, boolean onlyActive) {
        // TODO: Implement getModels logic
        return null;
    }
}