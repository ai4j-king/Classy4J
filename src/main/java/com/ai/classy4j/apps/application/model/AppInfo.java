package com.ai.classy4j.apps.application.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class AppInfo {
    private String appId;
    private String name;
    private String type;
    private String description;
    private List<String> tags;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private AppInfo.AppConfig appConfig;
    private AppInfo.ModelConfig modelConfig;


    @Data
    public static class ModelConfig {
        private String modelName;
//        private Double temperature;
//        private Integer maxTokens;
    }

    @Data
    public static class AppConfig {
        private ModelConfig modelConfig;
        private Map<String, Object> configs;
    }
}