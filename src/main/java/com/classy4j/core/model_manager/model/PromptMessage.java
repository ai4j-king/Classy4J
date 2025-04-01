package com.classy4j.core.model_manager.model;

/**
 * 提示消息
 */
public class PromptMessage implements Message {
    private String content;

    public PromptMessage(String prompt) {
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}