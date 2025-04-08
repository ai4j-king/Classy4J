package com.classy4j.core.app.generator;

import com.classy4j.config.LLMConfig;
import com.classy4j.model.*;
import com.classy4j.service.ProviderService;

import com.google.common.collect.Lists;
import io.github.pigmesh.ai.deepseek.core.OpenAiClient;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;
import org.testcontainers.shaded.com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


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

        // 创建OpenAI客户端
        OpenAiApi openAiApi = new OpenAiApi(config.getEndpointUrl(),config.getApiKeyDecrypt());

        // 动态创建OpenAiChatModel
        OpenAiChatOptions customOptions = OpenAiChatOptions.builder()
                .model(modelConfig.getModel().getName()).build();
        OpenAiChatModel chatModel = new OpenAiChatModel(openAiApi,customOptions);


        // 生成回复
        String  answer= chatModel.call(new SystemMessage(modelConfig.getPrePrompt()),new UserMessage(request.getQuery()));

        Map<String, Object> response = Maps.newHashMap();
        response.put("answer", answer);
        return response;
    }
}