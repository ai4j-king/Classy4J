package com.classy4j.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.classy4j.model.AppModelConfig;
import com.classy4j.repository.AppModelConfigRepository;

@Service
public class AppModelConfigService {
    @Autowired
    private AppModelConfigRepository appModelConfigRepository;

    public void validateConfiguration(AppModelConfig config, String mode) {
        // TODO: 实现配置验证逻辑
        // 1. 检查配置是否包含必要的字段
        // 2. 验证字段值的合法性
        // 3. 根据不同的应用模式进行特定验证
        if (config == null) {
            throw new IllegalArgumentException("Configuration cannot be null");
        }

        // 验证必要字段
        if (config.getProvider() == null || config.getProvider().isEmpty()) {
            throw new IllegalArgumentException("Provider is required");
        }
        if (config.getModelId() == null || config.getModelId().isEmpty()) {
            throw new IllegalArgumentException("Model ID is required");
        }
    }


    @Transactional
    public AppModelConfig create(AppModelConfig appModelConfig) {
        if (appModelConfigRepository.existsByAppId(appModelConfig.getAppId())) {
            throw new IllegalArgumentException("AppModelConfig with the same name already exists for this tenant");
        }
        LocalDateTime now = LocalDateTime.now();
        appModelConfig.setCreatedAt(now);
        appModelConfig.setUpdatedAt(now);
        return appModelConfigRepository.save(appModelConfig);
    }

    @Transactional
    public AppModelConfig update(AppModelConfig appModelConfig) {
        Optional<AppModelConfig> optionalAppModelConfig =
                appModelConfigRepository.findById(appModelConfig.getId());
        if (optionalAppModelConfig.isEmpty()) {
            throw new IllegalArgumentException("AppModelConfig not found");
        }
        appModelConfig.setCreatedAt(optionalAppModelConfig.get().getCreatedAt());
        return appModelConfigRepository.save(appModelConfig);
    }

    public void delete(String id) {
        appModelConfigRepository.deleteById(id);
    }
}