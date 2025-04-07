package com.classy4j.core.model_manager.model;

/**
 * 模型类型枚举
 */
public enum ModelType {
    LLM,    // 大语言模型
    EMBEDDING,  // 嵌入模型
    RERANK,     // 重排序模型
    MODERATION;  // 审核模型

    public static ModelType getModelType(String modelType) {
        for (ModelType type : ModelType.values()) {
            if (type.name().equalsIgnoreCase(modelType)) {
                return type;
            }
        }
        return null;
    }

//    contains
    public static boolean contains(String modelType) {
        for (ModelType type : ModelType.values()) {
            if (type.name().equalsIgnoreCase(modelType)) {
                return true;
            }
        }
        return false;
    }
}