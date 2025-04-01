package com.classy4j.core.model_manager.model;

import java.util.List;
import java.util.Map;

/**
 * 模型实例接口
 * @author changyadai
 */
public interface ModelInstance {

    /**
     * 调用LLM模型
     *
     * @param promptMessages 提示消息列表
     * @param modelParameters 模型参数
     * @param stream 是否使用流式响应
     * @return LLM调用结果
     */
    LLMResult invokeLLM(List<PromptMessage> promptMessages, Map<String, Object> modelParameters, boolean stream);
}