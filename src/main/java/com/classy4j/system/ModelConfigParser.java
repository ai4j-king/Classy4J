package com.classy4j.system;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class ModelConfigParser {
    private final ObjectMapper objectMapper;

    public ModelConfigParser() {
        objectMapper = new ObjectMapper();
        // 配置属性名称转换策略，将 snake_case 转换为 camelCase
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        // 启用忽略未知字段功能
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<ModelConfig> parseDirectory(File file){
//        file.listFiles() to List<ModelConfig>;
          if(ArrayUtils.isEmpty(file.listFiles())){
              return Lists.newArrayList();
          }
          return Lists.newArrayList(file.listFiles()).stream().map(this::parse)
                  .filter(modelConfig -> modelConfig != null).toList();
    }

    public ModelConfig parse(File file) {
        try {
            return objectMapper.readValue(file, ModelConfig.class);
        }catch (Exception e){
            log.error("Failed to parse model config file: " + e.getMessage());
        }
        return null;
    }


    public ModelConfig parse(InputStream input) {
        try {
            return objectMapper.readValue(input, ModelConfig.class);
        } catch (IOException e) {
            log.error("Failed to parse model config file: " + e.getMessage());
        }
        return null;
    }

    public ModelProvider parseModelProvider(InputStream input) {
        try {
            return objectMapper.readValue(input, ModelProvider.class);
        } catch (IOException e) {
            log.error("Failed to parse model config file: " + e.getMessage());
        }
        return null;
    }


}
