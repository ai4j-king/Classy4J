package com.classy4j.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.classy4j.entity.bo.ModelConfigReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.classy4j.model.App;
import com.classy4j.model.AppModelConfig;
import com.classy4j.repository.AppModelConfigRepository;
import com.classy4j.repository.AppRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AppService {
    @Autowired
    private AppRepository appRepository;

    @Autowired
    private AppModelConfigRepository appModelConfigRepository;

    private static final String DEFAULT_TEMPLATES_PATH = "templates/default_app_templates.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, Object> getDefaultAppTemplate(String mode) {
        try {
            ClassPathResource resource = new ClassPathResource(DEFAULT_TEMPLATES_PATH);
            Map<String, Object> templates = objectMapper.readValue(resource.getInputStream(), Map.class);
            return (Map<String, Object>) templates.get(mode);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load default app template", e);
        }
    }

    public Page<App> getPaginateApps(UUID userId, UUID tenantId, int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page - 1, limit);
        return appRepository.findByCreatedByAndTenantId(userId, tenantId, pageRequest);
    }

    public Optional<App> getApp(UUID id) {
        return appRepository.findById(id);
    }

    @Transactional
    public App createApp(UUID userId, UUID tenantId, String name, String description, String mode) {
        // Get default template based on mode
        Map<String, Object> template = getDefaultAppTemplate(mode);

        App app = new App();
        app.setTenantId(tenantId);
        app.setName(name);
        app.setDescription(description != null ? description : "");
        app.setMode(mode);
        app.setStatus("normal");
        app.setEnableSite(false);
        app.setEnableApi(false);

        // Apply template settings
        if (template != null) {
            app.setIconType("emoji");
            app.setIcon((String) template.get("icon"));
            app.setIconBackground((String) template.get("icon_background"));
        }

        LocalDateTime now = LocalDateTime.now();
        app.setCreatedAt(now);
        app.setUpdatedAt(now);
        app.setCreatedBy(userId);
        app.setUpdatedBy(userId);

        App appNew = appRepository.save(app);

        // Create default model config
        AppModelConfig modelConfig = new AppModelConfig();
        modelConfig.setAppId(appNew.getId());
        // Get default model and provider from template
        Map<String, Object> modelTemplate = getDefaultAppTemplate(mode);
        ModelConfigReq.Model model = new ModelConfigReq.Model();
        model.setMode(mode);
        model.setName((String) modelTemplate.get("default_model"));
        model.setProvider((String) modelTemplate.get("default_provider"));

        modelConfig.setProvider((String) modelTemplate.get("default_provider"));
        modelConfig.setModel(model);

        modelConfig.setCreatedAt(now);
        modelConfig.setUpdatedAt(now);
        modelConfig.setCreatedBy(userId);
        modelConfig.setUpdatedBy(userId);

        appModelConfigRepository.save(modelConfig);
        // Associate model config with app
        appNew.setAppModelConfigId(modelConfig.getId());
        appRepository.save(appNew); // 重新保存 app 以更新 appModelConfigId

        return appNew;
    }

    @Transactional
    public App updateApp(App app, String name, String description) {
        app.setName(name);
        app.setDescription(description != null ? description : app.getDescription());
        app.setUpdatedAt(LocalDateTime.now());
        return appRepository.save(app);
    }

    @Transactional
    public App update(App app) {
        if (app.getId() == null) {
            throw new IllegalArgumentException("App id cannot be null");
        }
        // 验证appModelConfigId是否存在
        if (app.getAppModelConfigId() != null) {
            appModelConfigRepository.findById(app.getAppModelConfigId())
                .orElseThrow(() -> new IllegalArgumentException("AppModelConfig not found"));
        }
        return appRepository.save(app);
    }

    @Transactional
    public App updateAppIcon(App app, String iconType, String icon, String iconBackground) {
        app.setIconType(iconType);
        app.setIcon(icon);
        app.setIconBackground(iconBackground);
        app.setUpdatedAt(LocalDateTime.now());
        return appRepository.save(app);
    }

    @Transactional
    public App updateAppSiteStatus(App app, boolean enableSite) {
        app.setEnableSite(enableSite);
        app.setUpdatedAt(LocalDateTime.now());
        return appRepository.save(app);
    }

    @Transactional
    public App updateAppApiStatus(App app, boolean enableApi) {
        app.setEnableApi(enableApi);
        app.setUpdatedAt(LocalDateTime.now());
        return appRepository.save(app);
    }

    @Transactional
    public void deleteApp(UUID id) {
        appRepository.deleteById(id);
    }
}