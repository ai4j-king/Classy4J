package com.classy4j.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ModelSettings {
    private Map<String, ModelLoadBalancingConfiguration> loadBalancingConfigurations;
    private Map<String, Map<String, Object>> modelSettings;
}