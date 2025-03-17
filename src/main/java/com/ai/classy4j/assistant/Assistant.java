package com.ai.classy4j.assistant;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface Assistant {

    @SystemMessage("You are a poliate assistant")
    String chat(String userMessage);
}
