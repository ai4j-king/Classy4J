package com.classy4j.model;

import java.util.Map;

import lombok.Data;

@Data
public class ModelConfiguration {
    private String modelType;
    private String model;
    private Map<String, Object> credentials;
}