package com.ai.classy4j.apps.chat.model;

import lombok.Data;

public class ChatRequest {
    @Data
    public static class SendMessageRequest {
        private String content;
        private String type = "text";
        private String language;
    }

    @Data
    public static class HistoryRequest {
        private int page = 1;
        private int pageSize = 20;
        private String startTime;
        private String endTime;
        private String keyword;
    }

    @Data
    public static class ExportRequest {
        private String startTime;
        private String endTime;
        private String format = "markdown";
    }
}