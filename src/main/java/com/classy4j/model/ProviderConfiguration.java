package com.classy4j.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ProviderConfiguration {
    private String tenantId;
    private Provider provider;
    private String preferredProviderType;
    private String usingProviderType;
    private SystemConfiguration systemConfiguration;
    private CustomConfiguration customConfiguration;
    private List<ModelConfiguration> modelSettings;

    public Map<String, Object> getCurrentCredentials(String modelType, String model) {
        if (modelSettings != null) {
            for (ModelConfiguration modelSetting : modelSettings) {
                if (modelSetting.getModelType().equals(modelType) && modelSetting.getModel().equals(model)) {
                    if (!modelSetting.getCredentials().isEmpty()) {
                        return modelSetting.getCredentials();
                    }
                }
            }
        }

        if ("SYSTEM".equals(usingProviderType)) {
            List<RestrictModel> restrictModels = new ArrayList<>();
            for (QuotaConfiguration quotaConfiguration : systemConfiguration.getQuotaConfigurations()) {
                if (systemConfiguration.getCurrentQuotaType().equals(quotaConfiguration.getQuotaType())) {
                    restrictModels = quotaConfiguration.getRestrictModels();
                    break;
                }
            }

            Map<String, Object> credentials = systemConfiguration.getCredentials() != null ?
                    new HashMap<>(systemConfiguration.getCredentials()) : new HashMap<>();

            if (!restrictModels.isEmpty()) {
                for (RestrictModel restrictModel : restrictModels) {
                    if (restrictModel.getModelType().equals(modelType) &&
                            restrictModel.getModel().equals(model) &&
                            restrictModel.getBaseModelName() != null) {
                        credentials.put("base_model_name", restrictModel.getBaseModelName());
                    }
                }
            }

            return credentials;
        } else {
            Map<String, Object> credentials = null;
            if (customConfiguration.getModels() != null) {
                for (ModelConfiguration modelConfig : customConfiguration.getModels()) {
                    if (modelConfig.getModelType().equals(modelType) && modelConfig.getModel().equals(model)) {
                        credentials = modelConfig.getCredentials();
                        break;
                    }
                }
            }

            if (credentials == null && customConfiguration.getProviderCredentials() != null) {
                credentials = customConfiguration.getProviderCredentials();
            }

            return credentials;
        }
    }

    public String getSystemConfigurationStatus() {
        if (Boolean.FALSE.equals(systemConfiguration.getEnabled())) {
            return "UNSUPPORTED";
        }

        String currentQuotaType = systemConfiguration.getCurrentQuotaType();
        QuotaConfiguration currentQuotaConfiguration = null;

        for (QuotaConfiguration quotaConfig : systemConfiguration.getQuotaConfigurations()) {
            if (quotaConfig.getQuotaType().equals(currentQuotaType)) {
                currentQuotaConfiguration = quotaConfig;
                break;
            }
        }

        if (currentQuotaConfiguration == null) {
            return null;
        }

        return currentQuotaConfiguration.getIsValid() ? "ACTIVE" : "QUOTA_EXCEEDED";
    }

    public boolean isCustomConfigurationAvailable() {
        return customConfiguration.getProviderCredentials() != null ||
                (customConfiguration.getModels() != null && !customConfiguration.getModels().isEmpty());
    }
}