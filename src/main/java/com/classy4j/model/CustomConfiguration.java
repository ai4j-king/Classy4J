package com.classy4j.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class CustomConfiguration {
    private Map<String, Object> providerCredentials;
    private List<ModelConfiguration> models;
}