package com.classy4j.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class SystemConfiguration {
    private Boolean enabled;
    private String currentQuotaType;
    private List<QuotaConfiguration> quotaConfigurations;
    private Map<String, Object> credentials;
}