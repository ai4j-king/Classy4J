package com.classy4j.core.model_manager.model;

import lombok.Data;

@Data
public class ProviderModelBundle {
    private String providerName;
    private String providerType;
    private String modelType;
    private String model;
    private String baseModelName;
    private String displayName;
    private String label;
    private String contextWindow;
    private String maxTokens;
    private String tokenLimit;
    private String enabled;
    private String status;
    private String supportFunctions;
    private String supportVision;
    private String supportAgentThought;
    private String supportTranscription;
    private String supportSpeech;
    private String supportModeration;
    private String supportEmbedding;
    private String supportRerank;
    private String supportLanguages;
    private String features;
    private String credentials;
    private String globalCredentials;
    private String modelCredentials;
}