package com.classy4j.core.app.config;

import java.util.List;
import java.util.Map;

/**
 * 文件上传配置管理器
 */
public class FileUploadConfigManager {
    private static final long DEFAULT_MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final List<String> DEFAULT_ALLOWED_FILE_TYPES = List.of(
            "text/plain",
            "application/pdf",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    );

    /**
     * 获取文件上传配置
     *
     * @param extraParams 额外参数
     * @return 文件上传配置
     */
    public static Map<String, Object> getFileUploadConfig(Map<String, Object> extraParams) {
        long maxFileSize = extraParams != null && extraParams.containsKey("maxFileSize")
                ? (long) extraParams.get("maxFileSize")
                : DEFAULT_MAX_FILE_SIZE;

        @SuppressWarnings("unchecked")
        List<String> allowedFileTypes = extraParams != null && extraParams.containsKey("allowedFileTypes")
                ? (List<String>) extraParams.get("allowedFileTypes")
                : DEFAULT_ALLOWED_FILE_TYPES;

        return Map.of(
                "maxFileSize", maxFileSize,
                "allowedFileTypes", allowedFileTypes
        );
    }

    /**
     * 验证文件类型是否允许上传
     *
     * @param contentType 文件类型
     * @param allowedFileTypes 允许的文件类型列表
     * @return 是否允许上传
     */
    public static boolean isFileTypeAllowed(String contentType, List<String> allowedFileTypes) {
        return allowedFileTypes.contains(contentType);
    }

    /**
     * 验证文件大小是否在限制范围内
     *
     * @param fileSize 文件大小
     * @param maxFileSize 最大文件大小
     * @return 是否在限制范围内
     */
    public static boolean isFileSizeAllowed(long fileSize, long maxFileSize) {
        return fileSize <= maxFileSize;
    }
}