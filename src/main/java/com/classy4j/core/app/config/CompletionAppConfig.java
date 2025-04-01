package com.classy4j.core.app.config;

import java.util.Map;

import com.classy4j.core.model_manager.model.ModelConfig;

/**
 * 应用配置类，用于管理应用级别的配置
 */
public class CompletionAppConfig {
    private ModelConfig modelConfig;
    private String promptTemplate;
    private Map<String, Object> extraParams;

    public ModelConfig getModelConfig() {
        return modelConfig;
    }

    public void setModelConfig(ModelConfig modelConfig) {
        this.modelConfig = modelConfig;
    }

    public String getPromptTemplate() {
        return promptTemplate;
    }

    public void setPromptTemplate(String promptTemplate) {
        this.promptTemplate = promptTemplate;
    }

    public Map<String, Object> getExtraParams() {
        return extraParams;
    }

    public void setExtraParams(Map<String, Object> extraParams) {
        this.extraParams = extraParams;
    }
}