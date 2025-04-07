package com.classy4j.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.classy4j.entity.bo.ModelConfigReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.classy4j.model.AppModelConfig;
import com.classy4j.repository.AppModelConfigRepository;

@Service
public class AppModelConfigService {
    @Autowired
    private AppModelConfigRepository appModelConfigRepository;


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
        appModelConfigRepository.deleteById(UUID.fromString(id));
    }
}