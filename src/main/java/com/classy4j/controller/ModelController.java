package com.classy4j.controller;

import com.classy4j.entity.bo.ModelResponse;
import com.classy4j.entity.bo.Response;
import com.classy4j.model.Account;
import com.classy4j.system.ModelConfig;
import com.classy4j.system.ModelConfigParser;
import com.classy4j.system.ModelProvider;
import com.google.common.collect.Lists;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/workspaces/current")
public class ModelController {

    private ModelConfigParser modelConfigParser = new ModelConfigParser();

    @GetMapping("/model-providers/{provider}/models/parameter-rules")
    public ResponseEntity<Response<List<ModelConfig.ParameterRule>>> getModelParameterRules(
//            @AuthenticationPrincipal Account currentUser,
            @PathVariable String provider,
            @RequestParam String model) {
        Pair<ModelProvider, List<ModelConfig>> providerListPair = getProviderAndConfig(provider);
        List<ModelConfig> modelConfigList = providerListPair.getRight();
        if (CollectionUtils.isEmpty(modelConfigList)){
            return ResponseEntity.ok(new Response<>(null));
        }
        ModelConfig modelConfig = modelConfigList.stream().filter(config -> config.getModel().equals(model)).findFirst().get();
        return ResponseEntity.ok(new Response<>(modelConfig.getParameterRules()));
    }

    @GetMapping("/models/model-types/{modelType}")
    public ResponseEntity<ModelResponse> getModelsByModelType(
            @PathVariable String modelType) {
        ModelResponse modelResponse = new ModelResponse();

        com.classy4j.entity.bo.ModelProvider modelProviderResp = getModelProvider("deepseek");
        com.classy4j.entity.bo.ModelProvider modelProviderResp2 = getModelProvider("tongyi");

        modelResponse.setData(Lists.newArrayList(modelProviderResp,modelProviderResp2));
        return ResponseEntity.ok(modelResponse);
    }

    private com.classy4j.entity.bo.@NotNull ModelProvider getModelProvider(String provider) {
        // 读取资源文件
        Pair<ModelProvider, List<ModelConfig>> providerListPair = getProviderAndConfig(provider);
        com.classy4j.entity.bo.ModelProvider modelProviderResp = new com.classy4j.entity.bo.ModelProvider();
        BeanUtils.copyProperties(providerListPair.getKey(), modelProviderResp);
        modelProviderResp.setModels(providerListPair.getRight());
        return modelProviderResp;
    }

    private Pair<ModelProvider, List<ModelConfig>> getProviderAndConfig(String provider) {
        InputStream inputStream = null;
        try {
            String path = String.format("modelprovider/%s/%s.json", provider, provider);
            // 读取资源文件
            ClassLoader classLoader = getClass().getClassLoader();
            inputStream = classLoader.getResourceAsStream(path);
            ModelProvider modelProvider = modelConfigParser.parseModelProvider(inputStream);
            if(modelProvider == null){
                return Pair.of(null, null);
            }
            String modelPath = String.format("modelprovider/%s/model", provider);
            String file = classLoader.getResource(modelPath).getFile();
            List<ModelConfig> modelConfigList = modelConfigParser.parseDirectory(new File(file));
            return Pair.of(modelProvider, modelConfigList);
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}