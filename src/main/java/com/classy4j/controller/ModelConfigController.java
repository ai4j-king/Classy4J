package com.classy4j.controller;

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

@RestController
@RequestMapping("/api/apps/{appId}")
@Transactional
public class ModelConfigController {
    @Autowired
    private AppService appService;

    @Autowired
    private AppModelConfigService appModelConfigService;

    @PostMapping("/model-config")
    public ResponseEntity<Map<String, String>> updateModelConfig(
            @PathVariable String appId,
            @RequestBody AppModelConfig config) {
        // 获取应用信息
        App app = appService.getApp(appId)
                .orElseThrow(() -> new IllegalArgumentException("App not found"));

        // 验证配置
        appModelConfigService.validateConfiguration(config, app.getMode());

        // 创建新的配置
        config.setAppId(appId);
        config.setId(app.getAppModelConfigId());

        // // 如果是Agent模式，需要处理工具参数的加密解密
        // if (app.getMode().equals("agent_chat")) {
        //     // 获取原始配置
        //     AppModelConfig originalConfig = appModelConfigService.findById(app.getAppModelConfigId())
        //             .orElseThrow(() -> new IllegalArgumentException("Original app model config not found"));

        //     // 处理Agent工具参数的加密解密
        //     String agentMode = config.getAgentMode();
        //     if (agentMode != null) {
        //         try {
        //             // TODO: 实现工具参数的加密解密逻辑
        //             // 1. 解密原始配置中的工具参数
        //             // 2. 对比新旧参数，保留加密的敏感信息
        //             // 3. 加密新配置中的工具参数
        //             newConfig.setAgentMode(agentMode);
        //         } catch (Exception e) {
        //             throw new IllegalArgumentException("Failed to process agent tool parameters: " + e.getMessage());
        //         }
        //     }
        // }

        // 保存新配置
       appModelConfigService.update(config);



        return ResponseEntity.ok(Map.of("result", "success"));
    }
}