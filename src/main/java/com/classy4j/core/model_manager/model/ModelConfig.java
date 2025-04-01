package com.classy4j.core.model_manager.model;

import java.util.Map;

/**
 * 模型配置类
 */
public class ModelConfig {
    private String provider;
    private String model;
    private String mode;
    private Map<String, Object> completionParams;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Map<String, Object> getCompletionParams() {
        return completionParams;
    }

    public void setCompletionParams(Map<String, Object> completionParams) {
        this.completionParams = completionParams;
    }
}