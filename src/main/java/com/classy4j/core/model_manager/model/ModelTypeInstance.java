package com.classy4j.core.model_manager.model;

import lombok.Data;

@Data
public class ModelTypeInstance {
    private String modelType;
    private String label;
    private String description;
    private String icon;
    private String iconBackground;
    private String enabled;
    private String status;
    private String supportModels;
    private String supportFeatures;
    private String credentials;
}