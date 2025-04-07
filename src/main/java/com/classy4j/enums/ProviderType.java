package com.classy4j.enums;

/**
 * 提供商类型枚举
 */
public enum ProviderType {
    SYSTEM,     // 系统内置提供商
    CUSTOM,     // 自定义提供商
    THIRD_PARTY; // 第三方提供商

    public static ProviderType getProviderType(String providerType) {
        for (ProviderType type : ProviderType.values()) {
            if (type.name().equalsIgnoreCase(providerType)) {
                return type;
            }
        }
        return null;
    }
}