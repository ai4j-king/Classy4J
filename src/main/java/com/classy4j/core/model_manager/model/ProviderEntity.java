package com.classy4j.core.model_manager.model;

import lombok.Data;

@Data
public class ProviderEntity {
    private String providerName;
    private String providerType;
    private String label;
    private String icon;
    private String iconBackground;
    private String description;
    private String helpText;
    private String documentationUrl;
    private String status;
    private String enabled;
    private String globalCredentials;
    private String modelCredentials;
    private String supportModels;
    private String supportModelTypes;
    private String features;
}