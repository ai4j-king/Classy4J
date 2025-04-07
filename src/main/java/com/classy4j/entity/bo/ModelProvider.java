package com.classy4j.entity.bo;

import com.classy4j.system.ModelConfig;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Represents a model provider with its configuration and available models
 */
public class ModelProvider {
    @JsonProperty("tenant_id")
    private String tenantId;
    
    private String provider;
    
    private Map<String, String> label;
    
    @JsonProperty("icon_small")
    private Map<String, String> iconSmall;
    
    @JsonProperty("icon_large")
    private Map<String, String> iconLarge;
    
    private String status;
    
    private List<ModelConfig> models;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Map<String, String> getLabel() {
        return label;
    }

    public void setLabel(Map<String, String> label) {
        this.label = label;
    }

    public Map<String, String> getIconSmall() {
        return iconSmall;
    }

    public void setIconSmall(Map<String, String> iconSmall) {
        this.iconSmall = iconSmall;
    }

    public Map<String, String> getIconLarge() {
        return iconLarge;
    }

    public void setIconLarge(Map<String, String> iconLarge) {
        this.iconLarge = iconLarge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ModelConfig> getModels() {
        return models;
    }

    public void setModels(List<ModelConfig> models) {
        this.models = models;
    }
}