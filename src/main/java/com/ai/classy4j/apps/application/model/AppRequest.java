package com.ai.classy4j.apps.application.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

public class AppRequest {
    @Data
    public static class CreateAppRequest {
        private String name;
        private String type;
        private String description;
        private List<String> tags;
    }

    @Data
    public static class UpdateAppConfigRequest {
        private AppInfo.ModelConfig modelConfig;
        private Map<String, Object> configs;
    }

    @Data
    public static class UpdateAppStatusRequest {
        private String status;
    }

    @Data
    public static class PageRequest {
        private int page = 1;
        private int pageSize = 10;
        private String keyword;
        private String type;
        private String status;
    }
}