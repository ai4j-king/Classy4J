package com.classy4j.core.app.entity;

import java.util.Map;

/**
 * 生成任务响应转换器
 */
public class CompletionAppGenerateResponseConverter {
    /**
     * 转换生成任务结果为标准响应格式
     *
     * @param result 生成任务结果
     * @return 标准响应格式
     */
    public static Map<String, Object> convert(Object result) {
        // TODO: 实现具体的转换逻辑
        return null;
    }

    /**
     * 转换流式生成任务结果为标准响应格式
     *
     * @param result 流式生成任务结果
     * @return 标准响应格式
     */
    public static Map<String, Object> convertStream(Object result) {
        // TODO: 实现具体的转换逻辑
        return null;
    }

    /**
     * 转换错误信息为标准响应格式
     *
     * @param error 错误信息
     * @return 标准响应格式
     */
    public static Map<String, Object> convertError(Throwable error) {
        // TODO: 实现具体的转换逻辑
        return null;
    }
}