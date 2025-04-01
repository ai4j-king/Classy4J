package com.classy4j.core.llm_generator.output_parser;

import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 建议问题输出解析器
 */
public class SuggestedQuestionsAfterAnswerOutputParser implements OutputParser<List<String>> {
    private static final String FORMAT_INSTRUCTIONS = """
        You are a helpful AI assistant. Based on the conversation history, please suggest 3 relevant follow-up questions that the user might be interested in asking.
        
        Your output should be in the following JSON format:
        {
            "questions": [
                "question1",
                "question2",
                "question3"
            ]
        }
        """;

    @Override
    public String getFormatInstructions() {
        return FORMAT_INSTRUCTIONS;
    }

    @Override
    public List<String> parse(String text) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            var result = mapper.readValue(text, SuggestedQuestionsResponse.class);
            return result.getQuestions();
        } catch (Exception e) {
            throw new OutputParserException("Failed to parse suggested questions output: " + e.getMessage());
        }
    }
}

/**
 * 建议问题响应类
 */
class SuggestedQuestionsResponse {
    private List<String> questions = new ArrayList<>();

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }
}