package com.classy4j.model;

import java.util.List;

import lombok.Data;

@Data
public class ModelLoadBalancingConfiguration {
    private String modelType;
    private String model;
    private List<String> models;
    private String strategy;
}