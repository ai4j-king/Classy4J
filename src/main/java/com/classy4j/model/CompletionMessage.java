package com.classy4j.model;

public class CompletionMessage {
    private String event;
    private String conversationId;
    private String messageId;
    private Long createdAt;
    private String taskId;
    private String id;
    private String answer;
    private Object fromVariableSelector;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Object getFromVariableSelector() {
        return fromVariableSelector;
    }

    public void setFromVariableSelector(Object fromVariableSelector) {
        this.fromVariableSelector = fromVariableSelector;
    }
}