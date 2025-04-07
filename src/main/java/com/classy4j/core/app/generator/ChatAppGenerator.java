package com.classy4j.core.app.generator;

import com.classy4j.config.LLMConfig;
import com.classy4j.model.*;
import com.classy4j.service.ProviderService;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.testcontainers.shaded.com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

/**
 * @author changyadai
 */
@Service
public class ChatAppGenerator {

    @Resource
    private ProviderService providerService;


    public CompletionResponse generate(CompletionRequest request) {
        // TODO: Implement chat generation logic
        return new CompletionResponse();
    }

    public Map<String, Object> generate(App app, CompletionRequest request) {
        AppModelConfig modelConfig = app.getAppModelConfig();
        String provider = modelConfig.getModel().getProvider();
        Provider providerInfo =providerService.findByProviderName(provider);
        LLMConfig config = providerInfo.getEncryptedConfig();
        ChatLanguageModel chatModel = OpenAiChatModel.builder()
                .baseUrl(config.getEndpointUrl())
                .apiKey(config.getApiKeyDecrypt())
                .modelName(modelConfig.getModel().getName())
                .logRequests(true)
                .logResponses(true)
                .build();
        List<ChatMessage> messages = new ArrayList<>();
        SystemMessage systemMessage = SystemMessage.from(modelConfig.getPrePrompt());
        messages.add(systemMessage);
        UserMessage userMessage = UserMessage.from(request.getQuery());
        messages.add(userMessage);
        ChatResponse chatResponse = chatModel.chat(messages);
        String answer = chatResponse.aiMessage().text();
        Map<String, Object> response = Maps.newHashMap();
        response.put("answer", answer);
        return response;
    }
}