package com.classy4j.controller;

import com.classy4j.model.CompletionRequest;
import com.classy4j.model.CompletionResponse;
import com.classy4j.model.CompletionMessage;
import com.classy4j.service.AppGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.bind.annotation.*;

import com.classy4j.model.App;
import com.classy4j.service.AppService;
import com.classy4j.exception.*;

import java.util.Map;
import java.io.IOException;
import java.time.Instant;

@RestController
@RequestMapping("/api/apps")
public class CompletionController {
    @Autowired
    private AppService appService;

    @Autowired
    private AppGenerateService appGenerateService;

    @PostMapping(value = "/{appId}/chat-messages")
    public ResponseEntity<?> generateCompletion(
            @PathVariable String appId,
            @RequestBody CompletionRequest request) {
        try {
            // 验证应用模式
            App app = appService.getApp(appId)
                    .orElseThrow(() -> new AppException("App not found"));
            
            if (!"chat".equals(app.getMode())) {
                throw new NotCompletionAppException("This app is not in chat mode");
            }

            // 处理生成请求
            Map<String, Object> response = appGenerateService.generate(
                    app,
                    request,false
            );

            CompletionResponse completionResponse = new CompletionResponse();
            completionResponse.setResult("success");
            completionResponse.setData(response);
            return ResponseEntity.ok(completionResponse);
        } catch (AppException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }

    @PostMapping("/messages/{taskId}/stop")
    public ResponseEntity<?> stopCompletion(
            @PathVariable String taskId) {
        try {
            // TODO: 实现停止生成逻辑
            return ResponseEntity.ok().body(Map.of("result", "success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }
}