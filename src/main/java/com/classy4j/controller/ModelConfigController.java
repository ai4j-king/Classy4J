package com.classy4j.controller;

import com.classy4j.entity.bo.ModelConfigReq;
import com.classy4j.repository.AppModelConfigRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.classy4j.model.App;
import com.classy4j.model.AppModelConfig;
import com.classy4j.service.AppModelConfigService;
import com.classy4j.service.AppService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/apps/{appId}")
@Transactional
public class ModelConfigController {
    @Autowired
    private AppService appService;

    @Autowired
    private AppModelConfigService appModelConfigService;

    @Autowired
    private AppModelConfigRepository appModelConfigRepository;

    @PostMapping("/model-config")
    public ResponseEntity<Map<String, String>> updateModelConfig(
            @PathVariable UUID appId,
            @RequestBody ModelConfigReq modelConfigReq) {
        // 获取应用信息
        App app = appService.getApp(appId)
                .orElseThrow(() -> new IllegalArgumentException("App not found"));

        AppModelConfig appModelConfig =  appModelConfigRepository.findById(app.getAppModelConfigId()).get();
        BeanUtils.copyProperties(modelConfigReq, appModelConfig);

        // 保存新配置
       appModelConfigService.update(appModelConfig);

        return ResponseEntity.ok(Map.of("result", "success"));
    }
}