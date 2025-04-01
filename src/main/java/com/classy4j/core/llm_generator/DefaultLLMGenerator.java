package com.classy4j.core.llm_generator;

import com.classy4j.core.model_manager.model.*;
import com.classy4j.core.model_manager.ModelManager;
import com.classy4j.core.llm_generator.output_parser.RuleConfigGeneratorOutputParser;
import com.classy4j.core.llm_generator.output_parser.SuggestedQuestionsAfterAnswerOutputParser;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LLM生成器默认实现
 */
public class DefaultLLMGenerator implements LLMGenerator {
    
    private final ModelManager modelManager;
    
    public DefaultLLMGenerator(ModelManager modelManager) {
        this.modelManager = modelManager;
    }
    
    @Override
    public String generateConversationName(String tenantId, String query, String conversationId, String appId) {
        // 处理查询内容
        if (query.length() > 2000) {
            query = query.substring(0, 300) + "...[TRUNCATED]..." + query.substring(query.length() - 300);
        }
        query = query.replace("\n", " ");
        
        // 获取模型实例
        ModelInstance modelInstance = modelManager.getDefaultModelInstance(tenantId, ModelType.LLM);
        
        // 构建提示消息
        List<PromptMessage> prompts = new ArrayList<>();
        prompts.add(new UserPromptMessage(query));
        
        // 调用模型
        Map<String, Object> modelParameters = new HashMap<>();
        modelParameters.put("max_tokens", 100);
        modelParameters.put("temperature", 1);
        
        LLMResult response = modelInstance.invokeLLM(prompts, modelParameters, false);
        
        // 解析响应
        String answer = response.getMessage().getContent();
        Pattern pattern = Pattern.compile("^.*(\\{.*\\}).*$", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(answer);
        
        if (!matcher.find()) {
            return "";
        }
        
        String jsonStr = matcher.group(1);
        Map<String, String> resultMap = new HashMap<>(); // TODO: 使用JSON解析
        String name = resultMap.get("Your Output").trim();
        
        if (name.length() > 75) {
            name = name.substring(0, 75) + "...";
        }
        
        return name;
    }
    
    @Override
    public List<String> generateSuggestedQuestionsAfterAnswer(String tenantId, String histories) {
        SuggestedQuestionsAfterAnswerOutputParser outputParser = new SuggestedQuestionsAfterAnswerOutputParser();
        String formatInstructions = outputParser.getFormatInstructions();
        
        PromptTemplateParser promptTemplate = new PromptTemplateParser("{{histories}}\n{{format_instructions}}\nquestions:\n");
        
        Map<String, String> inputs = new HashMap<>();
        inputs.put("histories", histories);
        inputs.put("format_instructions", formatInstructions);
        
        String prompt = promptTemplate.format(inputs, false);
        
        try {
            ModelInstance modelInstance = modelManager.getDefaultModelInstance(tenantId, ModelType.LLM);
            
            List<PromptMessage> promptMessages = new ArrayList<>();
            promptMessages.add(new UserPromptMessage(prompt));
            
            Map<String, Object> modelParameters = new HashMap<>();
            modelParameters.put("max_tokens", 256);
            modelParameters.put("temperature", 0);
            
            LLMResult response = modelInstance.invokeLLM(promptMessages, modelParameters, false);
            
            return outputParser.parse(response.getMessage().getContent());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    @Override
    public Map<String, Object> generateRuleConfig(String tenantId, String instruction, Map<String, Object> modelConfig,
            boolean noVariable, int ruleConfigMaxTokens) {
        RuleConfigGeneratorOutputParser outputParser = new RuleConfigGeneratorOutputParser();
        
        Map<String, Object> ruleConfig = new HashMap<>();
        ruleConfig.put("prompt", "");
        ruleConfig.put("variables", new ArrayList<>());
        ruleConfig.put("opening_statement", "");
        ruleConfig.put("error", "");
        
        Map<String, Object> modelParameters = new HashMap<>();
        modelParameters.put("max_tokens", ruleConfigMaxTokens);
        modelParameters.put("temperature", 0.01);
        
        if (noVariable) {
            // TODO: 实现无变量规则配置生成
            return ruleConfig;
        }
        
        // TODO: 实现带变量规则配置生成
        return ruleConfig;
    }
}