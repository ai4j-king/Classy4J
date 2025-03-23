package com.ai.classy4j.apps.application.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.classy4j.apps.application.entity.AppEntity;
import com.ai.classy4j.apps.application.model.AppInfo;
import com.ai.classy4j.apps.application.model.AppRequest;
import com.ai.classy4j.apps.application.repository.AppRepository;
import com.ai.classy4j.apps.application.service.AppService;
import com.ai.classy4j.apps.common.BusinessException;

@Service
@Slf4j
public class AppServiceImpl implements AppService {
    @Autowired
    private AppRepository appRepository;

    @Override
    public AppInfo createApp(AppRequest.CreateAppRequest request) {
        // 验证应用名称唯一性
        if (appRepository.existsByName(request.getName())) {
            throw new BusinessException(400, "应用名称已存在");
        }

        AppInfo appInfo = new AppInfo();
        appInfo.setAppId(UUID.randomUUID().toString());
        appInfo.setName(request.getName());
        appInfo.setType(request.getType());
        appInfo.setDescription(request.getDescription());
        appInfo.setTags(request.getTags());
        appInfo.setStatus("enabled");
        appInfo.setCreateTime(LocalDateTime.now());
        appInfo.setUpdateTime(LocalDateTime.now());

        AppEntity entity = AppEntity.fromModel(appInfo);
        entity = appRepository.save(entity);
        return entity.toModel();
    }

    @Override
    public void updateAppConfig(String appId, AppRequest.UpdateAppConfigRequest request) {
        log.info("开始更新应用配置，appId={}, request={}", appId, request);
        try {
            AppEntity entity = appRepository.findById(appId)
                .orElseThrow(() -> new BusinessException(404, "应用不存在"));
            log.info("查询到应用实体：{}", entity);

            AppInfo.AppConfig config = new AppInfo.AppConfig();
            config.setModelConfig(request.getModelConfig());
            config.setConfigs(request.getConfigs());

            entity.setAppConfig(config);
            entity.setUpdateTime(LocalDateTime.now());
            log.info("更新后的实体状态：{}", entity);

            AppEntity savedEntity = appRepository.save(entity);
            log.info("应用配置更新成功，savedEntity={}", savedEntity);
        } catch (Exception e) {
            log.error("更新应用配置失败，appId={}，错误信息：{}", appId, e.getMessage(), e);
            throw e;
        }
    }
    @Override
    public void updateAppStatus(String appId, AppRequest.UpdateAppStatusRequest request) {
        AppEntity entity = appRepository.findById(appId)
            .orElseThrow(() -> new BusinessException(404, "应用不存在"));

        if (!"enabled".equals(request.getStatus()) && !"disabled".equals(request.getStatus())) {
            throw new BusinessException(400, "无效的状态值");
        }

        entity.setStatus(request.getStatus());
        entity.setUpdateTime(LocalDateTime.now());
        appRepository.save(entity);
    }

    @Override
    public void deleteApp(String appId) {
        if (!appRepository.existsById(appId)) {
            throw new BusinessException(404, "应用不存在");
        }
        appRepository.deleteById(appId);
    }

    @Override
    public List<AppInfo> listApps(AppRequest.PageRequest request) {
        List<AppEntity> entities = appRepository.findByFilters(
            request.getKeyword(),
            request.getType(),
            request.getStatus()
        );

        // 分页处理
        int start = (request.getPage() - 1) * request.getPageSize();
        int end = Math.min(start + request.getPageSize(), entities.size());
        return entities.subList(start, end).stream()
            .map(AppEntity::toModel)
            .toList();
    }

    @Override
    public AppInfo getAppById(String appId) {
        return appRepository.findById(appId)
            .map(AppEntity::toModel)
            .orElse(null);
    }
}