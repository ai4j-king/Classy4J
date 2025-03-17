package com.ai.classy4j.controller;

import com.ai.classy4j.assistant.Assistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssistantController {

    @Autowired
    Assistant assistant;

    @GetMapping("/assistant/chat")
    public String chat(String prompt){
        return assistant.chat(prompt);
    }
}
