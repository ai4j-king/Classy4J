package com.classy4j.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LLMConfig {
    @JsonProperty("api_key")
    private String apiKey;

    @JsonProperty("api_key_decrypt")
    private String apiKeyDecrypt;

    @JsonProperty("endpoint_url")
    private String endpointUrl;

    @JsonProperty("function_calling_type")
    private String functionCallingType;

    private String mode;

    @JsonProperty("stream_function_calling")
    private String streamFunctionCalling;
}