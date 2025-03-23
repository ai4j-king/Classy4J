package com.ai.classy4j.apps.application.entity.converter;

import com.ai.classy4j.apps.application.model.AppInfo.AppConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AppConfigConverter implements AttributeConverter<AppConfig, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(AppConfig attribute) {
        try {
            return attribute == null ? null : objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting AppConfig to JSON string", e);
        }
    }

    @Override
    public AppConfig convertToEntityAttribute(String dbData) {
        try {
            return dbData == null ? null : objectMapper.readValue(dbData, AppConfig.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting JSON string to AppConfig", e);
        }
    }
}