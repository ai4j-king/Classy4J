package com.classy4j.core.llm_generator.output_parser;

/**
 * 输出解析异常类
 */
public class OutputParserException extends RuntimeException {
    
    public OutputParserException(String message) {
        super(message);
    }
    
    public OutputParserException(String message, Throwable cause) {
        super(message, cause);
    }
}