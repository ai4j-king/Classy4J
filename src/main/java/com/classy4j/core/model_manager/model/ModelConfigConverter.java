package com.classy4j.core.model_manager.model;

import java.util.HashMap;
import java.util.Map;

import com.classy4j.model.AppModelConfig;

/**
 * 模型配置转换器，用于在不同模型配置格式之间进行转换
 */
public class ModelConfigConverter {

    /**
     * 将模型配置转换为Map格式
     *
     * @param modelConfig 模型配置
     * @return Map格式的模型配置
     */
    public static Map<String, Object> convertToMap(ModelConfig modelConfig) {
        if (modelConfig == null) {
            return null;
        }

        Map<String, Object> configMap = new HashMap<>();
        configMap.put("provider", modelConfig.getProvider());
        configMap.put("model", modelConfig.getModel());
        configMap.put("mode", modelConfig.getMode());
        configMap.put("completion_params", modelConfig.getCompletionParams());

        return configMap;
    }

    /**
     * 从Map格式转换为模型配置
     *
     * @param configMap Map格式的模型配置
     * @return 模型配置
     */
    public static ModelConfig convertFromMap(Map<String, Object> configMap) {
        if (configMap == null) {
            return null;
        }

        ModelConfig modelConfig = new ModelConfig();
        modelConfig.setProvider((String) configMap.get("provider"));
        modelConfig.setModel((String) configMap.get("model"));
        modelConfig.setMode((String) configMap.get("mode"));

        @SuppressWarnings("unchecked")
        Map<String, Object> completionParams = (Map<String, Object>) configMap.get("completion_params");
        modelConfig.setCompletionParams(completionParams);

        return modelConfig;
    }
}