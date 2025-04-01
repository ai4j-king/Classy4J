package com.classy4j.core.llm_generator.output_parser;

/**
 * 输出解析器接口，用于处理模型输出的解析
 */
public interface OutputParser<T> {
    
    /**
     * 获取格式化指令
     *
     * @return 格式化指令字符串
     */
    String getFormatInstructions();

    /**
     * 解析模型输出
     *
     * @param text 模型输出文本
     * @return 解析后的结果
     */
    T parse(String text);
}