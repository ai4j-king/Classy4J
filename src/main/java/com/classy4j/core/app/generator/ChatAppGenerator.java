package com.classy4j.core.app.generator;

import com.classy4j.model.App;
import com.classy4j.model.CompletionRequest;
import com.classy4j.model.CompletionResponse;
import com.classy4j.model.EndUser;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import jakarta.annotation.Resource;
import org.testcontainers.shaded.com.google.common.collect.Maps;

import java.util.Map;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

/**
 * @author changyadai
 */
@Resource
public class ChatAppGenerator {


    public CompletionResponse generate(CompletionRequest request) {
        // TODO: Implement chat generation logic
        return new CompletionResponse();
    }

    public Map<String, Object> generate(App app, CompletionRequest request) {

        ChatLanguageModel chatModel = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName(GPT_4_O_MINI)
                .logRequests(true)
                .build();

        Map<String, Object> response = Maps.newHashMap();
        response.put("answer", "Hello, World!");
        return response;    }
}