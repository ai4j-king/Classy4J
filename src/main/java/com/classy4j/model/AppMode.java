package com.classy4j.model;

/**
 * 应用程序运行模式的枚举类
 */
public enum AppMode {
    /**
     * 完成模式
     */
    COMPLETION("completion"),

    /**
     * 工作流模式
     */
    WORKFLOW("workflow"),

    /**
     * 聊天模式
     */
    CHAT("chat"),

    /**
     * 高级聊天模式
     */
    ADVANCED_CHAT("advanced-chat"),

    /**
     * 智能代理聊天模式
     */
    AGENT_CHAT("agent-chat"),

    /**
     * 频道模式
     */
    CHANNEL("channel");

    private final String value;

    AppMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * 根据字符串值获取对应的枚举实例
     *
     * @param value 模式值
     * @return 对应的AppMode枚举实例
     * @throws IllegalArgumentException 如果没有找到匹配的枚举值
     */
    public static AppMode fromValue(String value) {
        for (AppMode mode : AppMode.values()) {
            if (mode.value.equals(value)) {
                return mode;
            }
        }
        throw new IllegalArgumentException("Invalid mode value: " + value);
    }
}