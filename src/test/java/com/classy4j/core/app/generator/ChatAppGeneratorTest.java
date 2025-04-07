package com.classy4j.core.app.generator;


import com.classy4j.model.App;
import com.classy4j.model.CompletionRequest;
import com.classy4j.model.CompletionResponse;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ChatAppGeneratorTest {

    @Mock
    private ChatLanguageModel chatModel;

    @InjectMocks
    private ChatAppGenerator chatAppGenerator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void generate_ValidInput_ReturnsAnswer() {
        CompletionRequest request = new CompletionRequest();
        request.setQuery("你好");
        App app = new App();

        when(chatModel.chat("你好")).thenReturn("你好，世界！");

        Map<String, Object> response = chatAppGenerator.generate(app, request);

        assertNotNull(response);
        assertEquals("你好，世界！", response.get("answer"));

        ChatLanguageModel chatModel = OpenAiChatModel.builder()
                .baseUrl("https://api.deepseek.com")
                .apiKey("sk-7bec1e1708dd4d1abfbdd6f0238d3add")
                .modelName("deepseek-chat")
                .logRequests(true)
                .build();
        String answer = chatModel.chat("你好");
        System.out.println(answer);

    }

    @Test
    public void generate_EmptyQuery_ReturnsEmptyAnswer() {
        CompletionRequest request = new CompletionRequest();
        request.setQuery("");
        App app = new App();

        when(chatModel.chat("")).thenReturn("");

        Map<String, Object> response = chatAppGenerator.generate(app, request);

        assertNotNull(response);
        assertEquals("", response.get("answer"));
    }

    @Test
    public void generate_InvalidApiKey_ThrowsException() {
        CompletionRequest request = new CompletionRequest();
        request.setQuery("你好");
        App app = new App();

        when(chatModel.chat("你好")).thenThrow(new RuntimeException("Invalid API key"));

        assertThrows(RuntimeException.class, () -> {
            chatAppGenerator.generate(app, request);
        });
    }

    @Test
    public void generate_ExceptionDuringModelBuilding_ThrowsException() {
        CompletionRequest request = new CompletionRequest();
        request.setQuery("你好");
        App app = new App();

        when(chatModel.chat("你好")).thenThrow(new RuntimeException("Model building failed"));

        assertThrows(RuntimeException.class, () -> {
            chatAppGenerator.generate(app, request);
        });
    }
}
