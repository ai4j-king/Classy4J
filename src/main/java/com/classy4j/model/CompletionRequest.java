package com.classy4j.model;

import java.util.List;
import java.util.Map;

public class CompletionRequest {
    private Map<String, Object> inputs;
    private String query;
    private List<Map<String, Object>> files;
    private String responseMode = "blocking";
    private String retrieverFrom = "web_app";
    private String conversationId;
    private String parentMessageId;
    private AppModelConfig modelConfig;


    public Map<String, Object> getInputs() {
        return inputs;
    }

    public void setInputs(Map<String, Object> inputs) {
        this.inputs = inputs;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Map<String, Object>> getFiles() {
        return files;
    }

    public void setFiles(List<Map<String, Object>> files) {
        this.files = files;
    }

    public String getResponseMode() {
        return responseMode;
    }

    public void setResponseMode(String responseMode) {
        this.responseMode = responseMode;
    }

    public String getRetrieverFrom() {
        return retrieverFrom;
    }

    public void setRetrieverFrom(String retrieverFrom) {
        this.retrieverFrom = retrieverFrom;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getParentMessageId() {
        return parentMessageId;
    }

    public void setParentMessageId(String parentMessageId) {
        this.parentMessageId = parentMessageId;
    }

    public AppModelConfig getModelConfig() {
        return modelConfig;
    }

    public void setModelConfig(AppModelConfig modelConfig) {
        this.modelConfig = modelConfig;
    }
}