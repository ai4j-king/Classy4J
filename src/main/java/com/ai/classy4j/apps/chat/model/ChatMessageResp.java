package com.ai.classy4j.apps.chat.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ChatMessageResp {
    private String messageId;
    private String appId;
    private String content;
    private String type;
    private String language;
    private LocalDateTime createTime;
    private AIResponse aiResponse;

    @Data
    public static class AIResponse {
        private String content;
        private String type;
    }
}