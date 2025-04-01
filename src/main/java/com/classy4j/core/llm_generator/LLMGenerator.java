package com.classy4j.core.llm_generator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * LLM生成器接口，定义了与大语言模型交互的核心功能
 */
public interface LLMGenerator {
    
    /**
     * 生成对话名称
     *
     * @param tenantId 租户ID
     * @param query 查询内容
     * @param conversationId 对话ID（可选）
     * @param appId 应用ID（可选）
     * @return 生成的对话名称
     */
    String generateConversationName(String tenantId, String query, String conversationId, String appId);

    /**
     * 生成回答后的建议问题
     *
     * @param tenantId 租户ID
     * @param histories 历史对话内容
     * @return 建议问题列表
     */
    List<String> generateSuggestedQuestionsAfterAnswer(String tenantId, String histories);

    /**
     * 生成规则配置
     *
     * @param tenantId 租户ID
     * @param instruction 指令内容
     * @param modelConfig 模型配置
     * @param noVariable 是否不包含变量
     * @param ruleConfigMaxTokens 规则配置最大token数
     * @return 规则配置信息
     */
    Map<String, Object> generateRuleConfig(String tenantId, String instruction, Map<String, Object> modelConfig, 
                                         boolean noVariable, int ruleConfigMaxTokens);
}