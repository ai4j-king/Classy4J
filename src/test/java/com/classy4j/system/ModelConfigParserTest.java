package com.classy4j.system;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ModelConfigParserTest {

    @Test
    public void parse_Test() throws Exception {
        // 读取资源文件
        ModelConfigParser modelConfigParser = new ModelConfigParser();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("modelprovider/deepseek/deepseek.json");
        assertNotNull(inputStream, "deepseek.json file not found");
        ModelProvider modelProvider = new ModelConfigParser().parseModelProvider(inputStream);
        System.out.println(JSONUtil.toJsonStr(modelProvider));

        String file = classLoader.getResource("modelprovider/deepseek/model").getFile();
        List<ModelConfig> modelConfigList = modelConfigParser.parseDirectory(new File(file));
        for (ModelConfig modelConfig : modelConfigList){
            System.out.println(JSONUtil.toJsonStr(modelConfig));
        }


    }
}
