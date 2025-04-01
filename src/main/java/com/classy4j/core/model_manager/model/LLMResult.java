package com.classy4j.core.model_manager.model;

/**
 * LLM调用结果
 */
public class LLMResult {
    private Message message;
    
    public Message getMessage() {
        return message;
    }
    
    public void setMessage(Message message) {
        this.message = message;
    }
}