package com.classy4j.core.llm_generator;

import com.classy4j.core.model_manager.model.ModelInstance;
import com.classy4j.core.model_manager.ModelManager;
import com.classy4j.core.model_manager.model.ModelType;
import com.classy4j.core.model_manager.model.PromptMessage;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * OpenAI LLM生成器实现类
 */
public class OpenAILLMGenerator implements LLMGenerator {
    private static final String CONVERSATION_TITLE_PROMPT = "";
    private final ModelManager modelManager;

    public OpenAILLMGenerator(ModelManager modelManager) {
        this.modelManager = modelManager;
    }

    @Override
    public String generateConversationName(String tenantId, String query, String conversationId, String appId) {
        String prompt = CONVERSATION_TITLE_PROMPT;

        // 处理过长的查询
        if (query.length() > 2000) {
            query = query.substring(0, 300) + "...[TRUNCATED]..." + query.substring(query.length() - 300);
        }

        query = query.replace("\n", " ");
        prompt += query + "\n";

        // 获取模型实例
        ModelInstance modelInstance = modelManager.getDefaultModelInstance(tenantId, ModelType.LLM);
        List<PromptMessage> prompts = List.of(new PromptMessage(prompt));

        // 调用模型生成对话名称
        Map<String, Object> modelParameters = Map.of(
            "max_tokens", 100,
            "temperature", 1
        );

        var result = modelInstance.invokeLLM(prompts, modelParameters, false);
        String answer = result.getMessage().getContent();

        // 提取JSON格式的回答
        Pattern pattern = Pattern.compile("\\{.*\\}");
        Matcher matcher = pattern.matcher(answer);
        if (!matcher.find()) {
            return "";
        }

        String jsonStr = matcher.group();
        // TODO: 解析JSON并提取Your Output字段
        String name = "";

        // 截断过长的名称
        if (name.length() > 75) {
            name = name.substring(0, 75) + "...";
        }

        return name;
    }

    @Override
    public List<String> generateSuggestedQuestionsAfterAnswer(String tenantId, String histories) {
        // TODO: 实现生成建议问题的逻辑
        return List.of();
    }

    @Override
    public Map<String, Object> generateRuleConfig(String tenantId, String instruction, Map<String, Object> modelConfig,
                                                boolean noVariable, int ruleConfigMaxTokens) {
        // TODO: 实现生成规则配置的逻辑
        return Map.of();
    }
}