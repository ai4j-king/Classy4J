package com.classy4j.core.llm_generator.output_parser;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 规则配置生成器输出解析器
 */
public class RuleConfigGeneratorOutputParser implements OutputParser<Map<String, Object>> {
    private static final String FORMAT_INSTRUCTIONS = """
        You are a rule config generator. I will provide you with a task description, and you need to help me generate a rule config.
        The rule config should include:
        1. A prompt that can guide the model to complete the task
        2. A list of variables that need to be replaced in the prompt
        3. An opening statement that introduces what this rule can do
        
        Your output should be in the following JSON format:
        {
            "prompt": "The prompt content",
            "variables": ["variable1", "variable2"],
            "opening_statement": "The opening statement"
        }
        """;

    @Override
    public String getFormatInstructions() {
        return FORMAT_INSTRUCTIONS;
    }

    @Override
    public Map<String, Object> parse(String text) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(text, Map.class);
        } catch (Exception e) {
            throw new OutputParserException("Failed to parse rule config output: " + e.getMessage());
        }
    }
}