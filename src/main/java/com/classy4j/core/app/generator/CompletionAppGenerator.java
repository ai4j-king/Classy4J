package com.classy4j.core.app.generator;

import com.classy4j.core.app.config.FileUploadConfigManager;
import com.classy4j.core.app.entity.CompletionAppGenerateResponseConverter;
import com.classy4j.core.app.exception.GenerateTaskStoppedError;
import com.classy4j.core.app.exception.InvokeAuthorizationError;
import com.classy4j.core.app.exception.ValidationError;
import com.classy4j.core.app.queue.TraceQueueManager;
import com.classy4j.core.model_manager.model.ModelConfigConverter;
import com.classy4j.model.*;
import com.classy4j.core.app.config.CompletionAppConfigManager;
import com.classy4j.core.app.entity.CompletionAppGenerateEntity;
import com.classy4j.core.app.queue.MessageBasedAppQueueManager;
import com.classy4j.core.app.runner.CompletionAppRunner;
import com.classy4j.service.AppService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.testcontainers.shaded.com.google.common.collect.Maps;


import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Component
public class CompletionAppGenerator {
    private static final Logger logger = Logger.getLogger(CompletionAppGenerator.class.getName());

    @Resource
    private AppService appService;
    
//    public CompletionResponse generate(CompletionRequest request) {
//        // 验证请求参数
//        String query = request.getQuery();
//        if (query == null || query.isEmpty()) {
//            throw new ValidationError("query must be a string");
//        }
//
//        query = query.replace("\u0000", "");
//        Map<String, Object> inputs = request.getInputs();
//
//        // 获取应用配置
//        App app = appService.getApp(request.getAppId()).get();
//        AppModelConfig appConfig = request.getModelConfig();
//        var appModelConfig = CompletionAppConfigManager.getAppModelConfig(app, null);
//
//        // 处理文件上传配置
//        var fileExtraConfig = FileUploadConfigManager.convert(appModelConfig.toMap());
//        var fileObjs = request.getFiles() != null ?
//            FileUploadConfigManager.buildFromMappings(request.getFiles(), app.getTenantId(), fileExtraConfig) :
//            List.of();
//
//        // 转换应用配置
////        var appConfig = CompletionAppConfigManager.getAppConfig(app, appModelConfig, null);
//
//        // 获取追踪实例
//        var traceManager = new TraceQueueManager(app.getId());
//
//        // 初始化生成实体
//        var generateEntity = new CompletionAppGenerateEntity(
//            UUID.randomUUID().toString(),
//            request.getModelConfig(),
//            ModelConfigConverter.convertToModelConfig(appConfig),
//            fileExtraConfig,
//            prepareUserInputs(inputs, appConfig.getVariables(), app.getTenantId()),
//            query,
//            fileObjs,
//            request.getUserId(),
//            request.isStreaming(),
//            request.getInvokeFrom(),
//            Map.of(),
//            traceManager
//        );
//
//        // 初始化生成记录
//        var records = initGenerateRecords(generateEntity);
//        var conversation = records.getLeft();
//        var message = records.getRight();
//
//        // 初始化队列管理器
//        var queueManager = new MessageBasedAppQueueManager(
//            generateEntity.getTaskId(),
//            generateEntity.getUserId(),
//            generateEntity.getInvokeFrom(),
//            conversation.getId(),
//            conversation.getMode(),
//            message.getId()
//        );
//
//        try {
//            // 直接执行生成任务
//            var runner = new CompletionAppRunner();
//            runner.run(generateEntity, queueManager, message);
//        } catch (GenerateTaskStoppedError e) {
//            // 任务被停止
//        } catch (InvokeAuthorizationError e) {
//            queueManager.publishError(new InvokeAuthorizationError("Incorrect API key provided"));
//        } catch (ValidationError e) {
//            logger.severe("Validation Error when generating");
//            queueManager.publishError(e);
//        } catch (Exception e) {
//            logger.severe("Unknown Error when generating");
//            queueManager.publishError(e);
//        }
//
//        // 处理响应
//        var response = handleResponse(generateEntity, queueManager, conversation, message, request.getUser());
//        return CompletionAppGenerateResponseConverter.convert(response, request.getInvokeFrom());
//    }
    
    public Map<String, Object> generate(App app, EndUser user, Map<String, Object> args, String invokeFrom, boolean streaming) {
        CompletionRequest request = new CompletionRequest();
//        request.setApp(app);
//        request.setUser(user);
//        request.setInputs(args);
//        request.setInvokeFrom(invokeFrom);
//        request.setStreaming(streaming);
        
//        CompletionResponse response = generate(request);
        Map<String, Object> response = Maps.newHashMap();
        response.put("answer", "Hello, World!");
        return response;
    }
}