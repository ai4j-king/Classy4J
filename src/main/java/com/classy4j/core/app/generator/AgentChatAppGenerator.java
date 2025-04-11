package com.classy4j.core.app.generator;

import com.classy4j.config.LLMConfig;
import com.classy4j.model.*;
import com.classy4j.service.ProviderService;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.model.function.FunctionCallbackResolver;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.retry.RetryUtils;
import org.springframework.stereotype.Service;
import org.testcontainers.shaded.com.google.common.collect.Maps;

import java.util.Map;

@Service
public class AgentChatAppGenerator {

    @Resource
    private ProviderService providerService;

    @Resource
    FunctionCallbackResolver functionCallbackResolver;

    public CompletionResponse generate(CompletionRequest request) {
        // TODO: Implement agent chat generation logic
        return new CompletionResponse();
    }

    public Map<String, Object> generate(App app, CompletionRequest request) {
        AppModelConfig modelConfig = app.getAppModelConfig();
        String provider = modelConfig.getModel().getProvider();
        Provider providerInfo = providerService.findByProviderName(provider);
        LLMConfig config = providerInfo.getEncryptedConfig();

        // 创建OpenAI客户端
        OpenAiApi openAiApi = new OpenAiApi(config.getEndpointUrl(),config.getApiKeyDecrypt());

        // 动态创建OpenAiChatModel
        OpenAiChatOptions customOptions = OpenAiChatOptions.builder()
                .model(modelConfig.getModel().getName()).build();
        OpenAiChatModel chatModel = new OpenAiChatModel(openAiApi,customOptions,
                functionCallbackResolver, RetryUtils.DEFAULT_RETRY_TEMPLATE);
        ChatClient chatClient = ChatClient.builder(chatModel)
                // 实现 Chat Memory 的 Advisor
                // 在使用 Chat Memory 时，需要指定对话 ID，以便 Spring AI 处理上下文。
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(new InMemoryChatMemory())
                )
                // 实现 Logger 的 Advisor
                .defaultAdvisors(
                        new SimpleLoggerAdvisor()
                )
                // 设置 ChatClient 中 ChatModel 的 Options 参数
                .defaultOptions(
                        OpenAiChatOptions.builder()
                                .topP(0.7)
                                .build()
                )
                .defaultTools("getWeatherServiceFunction")
                .build();
        String answer = chatClient.prompt().system(modelConfig.getPrePrompt())
                .user(request.getQuery()).call().content();

        Map<String, Object> response = Maps.newHashMap();
        response.put("answer", answer);
        return response;
    }
}