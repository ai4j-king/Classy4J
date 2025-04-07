package com.classy4j.core.app.generator;

import com.classy4j.config.LLMConfig;
import com.classy4j.model.*;
import com.classy4j.service.ProviderService;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.testcontainers.shaded.com.google.common.collect.Maps;

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
                .build();
        String answer = chatModel.chat(request.getQuery());
        Map<String, Object> response = Maps.newHashMap();
        response.put("answer", answer);
        return response;
    }
}