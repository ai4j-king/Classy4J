package com.ai.classy4j.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import dev.langchain4j.model.chat.ChatLanguageModel;

@RestController
public class LangChainController {

    @Resource
    private ChatLanguageModel chatLanguageModel;

    @GetMapping("/langchain/chat")
    public String chat(@RequestParam String prompt) {
        System.out.println("prompt:"+prompt);

        String result = chatLanguageModel.chat(prompt);

        System.out.println("调用大模型结果："+result);

        return result;
    }

    @PostMapping("/langchain/chat-post")
    public String chatPost(@RequestBody String message) {
        System.out.println("prompt:"+message);

        String result = chatLanguageModel.chat(message);

        System.out.println("调用大模型结果："+result);

        return result;
    }
}
