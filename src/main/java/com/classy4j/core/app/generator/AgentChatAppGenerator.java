package com.classy4j.core.app.generator;

import com.classy4j.model.App;
import com.classy4j.model.CompletionRequest;
import com.classy4j.model.CompletionResponse;
import com.classy4j.model.EndUser;

import java.util.Map;

public class AgentChatAppGenerator {
    public CompletionResponse generate(CompletionRequest request) {
        // TODO: Implement agent chat generation logic
        return new CompletionResponse();
    }

    public Map<String, Object> generate(App app, EndUser user, Map<String, Object> args, String invokeFrom, boolean streaming) {
        return null;
    }
}