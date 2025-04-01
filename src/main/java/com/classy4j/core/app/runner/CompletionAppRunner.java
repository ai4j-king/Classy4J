package com.classy4j.core.app.runner;


import com.classy4j.core.app.config.PromptTemplate;
import com.classy4j.core.app.entity.CompletionAppGenerateEntity;
import com.classy4j.core.app.exception.ModerationError;
import com.classy4j.core.app.exception.ValidationError;
import com.classy4j.core.app.queue.AppQueueManager;
import com.classy4j.core.app.tools.ExternalDataTool;
import com.classy4j.core.model_manager.model.Message;
import com.classy4j.core.model_manager.model.ModelConfig;
import com.classy4j.core.model_manager.model.ModelInstance;
import com.classy4j.core.model_manager.model.PromptMessage;
import com.classy4j.core.app.config.CompletionAppConfig;
import com.classy4j.model.App;
import com.classy4j.model.AppModelConfig;


import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CompletionAppRunner {
    private static final Logger logger = Logger.getLogger(CompletionAppRunner.class.getName());

    public void run(CompletionAppGenerateEntity entity, AppQueueManager queueManager, Message message) {

    }

//    public void run(CompletionAppGenerateEntity entity, AppQueueManager queueManager, Message message) {
//        AppModelConfig appConfig =  entity.getAppConfig();
//
//        // 获取App记录
//        App app = getApp(appConfig.getAppId());
//        if (app == null) {
//            throw new ValidationError("App not found");
//        }
//
//        Map<String, Object> inputs = entity.getInputs();
//        String query = entity.getQuery();
//        List<Object> files = entity.getFiles();
//
//        // 预计算提示消息的token数量
//        // 包括：提示模板、输入、查询(可选)、文件(可选)
//        // 不包括：记忆、外部数据、数据集上下文
//        int restTokens = calculateRestTokens(
//            app,
//            entity.getModelConf(),
//            appConfig.getPromptTemplate(),
//            inputs,
//            files,
//            query
//        );
//
//        // 组织所有输入和模板到提示消息
//        List<PromptMessage> promptMessages = organizePromptMessages(
//            app,
//            entity.getModelConf(),
//            appConfig.getPromptTemplate(),
//            inputs,
//            files,
//            query
//        );
//        List<String> stop = getStopSequences(appConfig);
//
//        try {
//            // 处理敏感词审核
//            processSensitiveWordAvoidance(
//                app.getId(),
//                appConfig.getTenantId(),
//                entity,
//                inputs,
//                query,
//                message.getId()
//            );
//        } catch (ModerationError e) {
//            directOutput(
//                queueManager,
//                entity,
//                promptMessages,
//                e.getMessage(),
//                entity.isStreaming()
//            );
//            return;
//        }
//
//        // 处理外部数据工具变量
//        if (appConfig.hasExternalDataTools()) {
//            inputs = fillInputsFromExternalDataTools(
//                app.getTenantId(),
//                app.getId(),
//                appConfig.getExternalDataTools(),
//                inputs,
//                query
//            );
//        }
//
//        // 获取数据集上下文
//        String context = null;
//        if (appConfig.hasDataset()) {
//            context = retrieveDatasetContext(
//                app,
//                entity,
//                appConfig,
//                inputs,
//                query,
//                message
//            );
//        }
//
//        // 重新组织所有输入和模板到提示消息
//        promptMessages = reorganizePromptMessages(
//            app,
//            entity.getModelConf(),
//            appConfig.getPromptTemplate(),
//            inputs,
//            files,
//            query,
//            context
//        );
//
//        // 检查托管审核
//        if (checkHostingModeration(entity, queueManager, promptMessages)) {
//            return;
//        }
//
//        // 重新计算最大token数
//        recalculateMaxTokens(entity.getModelConf(), promptMessages);
//
//        // 调用模型
//        ModelInstance modelInstance = new ModelInstance(
//            entity.getModelConf().getProviderModelBundle(),
//            entity.getModelConf().getModel()
//        );
//
//        var invokeResult = modelInstance.invokeLLM(
//            promptMessages,
//            entity.getModelConf().getParameters(),
//            stop,
//            entity.isStreaming(),
//            entity.getUserId()
//        );
//
//        // 处理调用结果
//        handleInvokeResult(invokeResult, queueManager, entity.isStreaming());
//    }

    // 以下是辅助方法的实现
    private App getApp(String appId) {
        // TODO: 实现从数据库获取App的逻辑
        return null;
    }

    private int calculateRestTokens(App app, ModelConfig modelConfig, PromptTemplate template,
                                  Map<String, Object> inputs, List<Object> files, String query) {
        // TODO: 实现token计算逻辑
        return 0;
    }

    private List<PromptMessage> organizePromptMessages(App app, ModelConfig modelConfig,
                                                       PromptTemplate template, Map<String, Object> inputs,
                                                       List<Object> files, String query) {
        // TODO: 实现提示消息组织逻辑
        return List.of();
    }

    private List<String> getStopSequences(AppModelConfig config) {
        // TODO: 实现获取停止序列逻辑
        return List.of();
    }

    private void processSensitiveWordAvoidance(String appId, String tenantId,
                                              CompletionAppGenerateEntity entity,
                                              Map<String, Object> inputs, String query,
                                              String messageId) {
        // TODO: 实现敏感词审核逻辑
    }

    private void directOutput(AppQueueManager queueManager, CompletionAppGenerateEntity entity,
                            List<PromptMessage> promptMessages, String text, boolean stream) {
        // TODO: 实现直接输出逻辑
    }

    private Map<String, Object> fillInputsFromExternalDataTools(String tenantId, String appId,
                                                              List<ExternalDataTool> tools,
                                                              Map<String, Object> inputs,
                                                              String query) {
        // TODO: 实现外部数据工具变量填充逻辑
        return inputs;
    }

    private String retrieveDatasetContext(App app, CompletionAppGenerateEntity entity,
                                        AppModelConfig config, Map<String, Object> inputs,
                                        String query, Message message) {
        // TODO: 实现数据集上下文检索逻辑
        return null;
    }

    private List<PromptMessage> reorganizePromptMessages(App app, ModelConfig modelConfig,
                                                       PromptTemplate template,
                                                       Map<String, Object> inputs,
                                                       List<Object> files, String query,
                                                       String context) {
        // TODO: 实现提示消息重组织逻辑
        return List.of();
    }

    private boolean checkHostingModeration(CompletionAppGenerateEntity entity,
                                         AppQueueManager queueManager,
                                         List<PromptMessage> promptMessages) {
        // TODO: 实现托管审核检查逻辑
        return false;
    }

    private void recalculateMaxTokens(ModelConfig modelConfig, List<PromptMessage> promptMessages) {
        // TODO: 实现最大token重新计算逻辑
    }

    private void handleInvokeResult(Object invokeResult, AppQueueManager queueManager, boolean stream) {
        // TODO: 实现调用结果处理逻辑
    }
}