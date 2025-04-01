package com.classy4j.core.app.entity;

import com.classy4j.core.app.config.CompletionAppConfig;
import com.classy4j.core.app.queue.TraceQueueManager;
import com.classy4j.core.model_manager.model.ModelConfig;
import com.classy4j.model.AppModelConfig;

import java.util.List;
import java.util.Map;

public class CompletionAppGenerateEntity {
    private final String taskId;
    private final AppModelConfig appConfig;
    private final ModelConfig modelConf;
    private final Map<String, Object> fileExtraConfig;
    private final Map<String, Object> inputs;
    private final String query;
    private final List<Object> files;
    private final String userId;
    private final boolean streaming;
    private final String invokeFrom;
    private final Map<String, Object> extraParams;
    private final TraceQueueManager traceManager;

    public CompletionAppGenerateEntity(String taskId,
                                       AppModelConfig appConfig,
                                      ModelConfig modelConf,
                                      Map<String, Object> fileExtraConfig,
                                      Map<String, Object> inputs,
                                      String query,
                                      List<Object> files,
                                      String userId,
                                      boolean streaming,
                                      String invokeFrom,
                                      Map<String, Object> extraParams,
                                      TraceQueueManager traceManager) {
        this.taskId = taskId;
        this.appConfig = appConfig;
        this.modelConf = modelConf;
        this.fileExtraConfig = fileExtraConfig;
        this.inputs = inputs;
        this.query = query;
        this.files = files;
        this.userId = userId;
        this.streaming = streaming;
        this.invokeFrom = invokeFrom;
        this.extraParams = extraParams;
        this.traceManager = traceManager;
    }

    public String getTaskId() {
        return taskId;
    }

    public AppModelConfig getAppConfig() {
        return appConfig;
    }

    public ModelConfig getModelConf() {
        return modelConf;
    }

    public Map<String, Object> getFileExtraConfig() {
        return fileExtraConfig;
    }

    public Map<String, Object> getInputs() {
        return inputs;
    }

    public String getQuery() {
        return query;
    }

    public List<Object> getFiles() {
        return files;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isStreaming() {
        return streaming;
    }

    public String getInvokeFrom() {
        return invokeFrom;
    }

    public Map<String, Object> getExtraParams() {
        return extraParams;
    }

    public TraceQueueManager getTraceManager() {
        return traceManager;
    }
}