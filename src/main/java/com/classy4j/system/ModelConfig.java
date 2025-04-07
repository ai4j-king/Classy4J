package com.classy4j.system;

import java.util.List;

import lombok.Data;

@Data
public class ModelConfig {
    private String model;
    private Label label;
    private String modelType;
    private List<String> features;
    private ModelProperties modelProperties;
    private List<ParameterRule> parameterRules;
    private Pricing pricing;



    @Data
    public static class ModelProperties {
        private String mode;
        private Integer contextSize;
    }

    @Data
    public static class ParameterRule {
        private String name;
        private String useTemplate;
        private String type;
        private Object defaultValue;
        private Object min;
        private Object max;
        private Help help;
        private Label label;
        private Boolean required;
        private List<String> options;
    }

    @Data
    public static class Help {
        private String zhHans;
        private String enUS;
    }

    @Data
    public static class Pricing {
        private String input;
        private String output;
        private String unit;
        private String currency;
    }
}