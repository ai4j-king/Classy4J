package com.classy4j.model;

import java.util.Map;

import lombok.Data;

@Data
public class CustomProviderConfiguration {
    private String provider;
    private Map<String, Object> credentials;
}