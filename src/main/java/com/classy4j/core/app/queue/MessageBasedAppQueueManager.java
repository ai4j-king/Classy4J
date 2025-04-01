package com.classy4j.core.app.queue;

public class MessageBasedAppQueueManager implements AppQueueManager {
    private final String taskId;
    private final String userId;
    private final String invokeFrom;
    private final String conversationId;
    private final String conversationMode;
    private final String messageId;

    public MessageBasedAppQueueManager(String taskId, String userId, String invokeFrom,
                                      String conversationId, String conversationMode, String messageId) {
        this.taskId = taskId;
        this.userId = userId;
        this.invokeFrom = invokeFrom;
        this.conversationId = conversationId;
        this.conversationMode = conversationMode;
        this.messageId = messageId;
    }

    @Override
    public void publishError(Throwable error) {
        // TODO: 实现错误消息发布逻辑
    }

    @Override
    public void publishMessage(String message) {
        // TODO: 实现消息发布逻辑
    }

    @Override
    public void publishStreamMessage(String message) {
        // TODO: 实现流式消息发布逻辑
    }

    @Override
    public void publishDone() {
        // TODO: 实现完成消息发布逻辑
    }

    @Override
    public void publishStop() {
        // TODO: 实现停止消息发布逻辑
    }

    @Override
    public String getTaskId() {
        return taskId;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getInvokeFrom() {
        return invokeFrom;
    }

    @Override
    public String getConversationId() {
        return conversationId;
    }

    @Override
    public String getConversationMode() {
        return conversationMode;
    }

    @Override
    public String getMessageId() {
        return messageId;
    }
}