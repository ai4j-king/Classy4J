package com.classy4j.core.app.queue;

public interface AppQueueManager {
    void publishError(Throwable error);
    void publishMessage(String message);
    void publishStreamMessage(String message);
    void publishDone();
    void publishStop();
    String getTaskId();
    String getUserId();
    String getInvokeFrom();
    String getConversationId();
    String getConversationMode();
    String getMessageId();
}