package com.classy4j.service;

import com.classy4j.core.app.features.rate_limiting.RateLimit;
import com.classy4j.core.app.generator.AgentChatAppGenerator;
import com.classy4j.core.app.generator.ChatAppGenerator;
import com.classy4j.core.app.generator.CompletionAppGenerator;
import com.classy4j.core.llm_generator.LLMGenerator;
import com.classy4j.core.model_manager.ModelManager;

import com.classy4j.exception.AppException;
import com.classy4j.exception.InvokeRateLimitError;
import com.classy4j.exception.RateLimitError;
import com.classy4j.model.App;
import com.classy4j.model.CompletionRequest;
import com.classy4j.model.EndUser;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 应用生成服务
 */
@Component
public class AppGenerateService {
    
    private  LLMGenerator llmGenerator;
    private  ModelManager modelManager;

    @Resource
    private ChatAppGenerator chatAppGenerator;
    
//    public AppGenerateService(LLMGenerator llmGenerator, ModelManager modelManager) {
//        this.llmGenerator = llmGenerator;
//        this.modelManager = modelManager;
//    }
    
    /**
     * 生成应用内容
     *
     * @param app 应用模型

     * @return 生成结果
     */
    public Map<String, Object> generate(App app, CompletionRequest request,boolean streaming) {
        int maxActiveRequest = getMaxActiveRequests(app);
        RateLimit rateLimit = RateLimit.getInstance(app.getId().toString(), maxActiveRequest);
        String requestId = RateLimit.genRequestKey();
        
        try {
            requestId = rateLimit.enter(requestId);
            
            switch (app.getMode()) {
                case "completion":
                    return null;
                case "agent_chat":
                    return null;
                case "chat":
                    return  chatAppGenerator.generate(app, request);
//                case "advanced_chat":
//                    Workflow workflow = getWorkflow(app, invokeFrom);
//                    return rateLimit.generate(
//                        new AdvancedChatAppGenerator().generate(
//                            app, workflow, user, args, invokeFrom, streaming
//                        ),
//                        requestId
//                    );
//                case "workflow":
//                    workflow = getWorkflow(app, invokeFrom);
//                    return rateLimit.generate(
//                        new WorkflowAppGenerator().generate(
//                            app, workflow, user, args, invokeFrom, streaming
//                        ),
//                        requestId
//                    );
                default:
                    throw new AppException("Invalid app mode " + app.getMode());
            }
        } catch (RateLimitError e) {
            throw new InvokeRateLimitError(e.getMessage());
        } catch (Exception e) {
            rateLimit.exit(requestId);
            throw e;
        } finally {
            if (!streaming) {
                rateLimit.exit(requestId);
            }
        }
    }
    
    /**
     * 获取最大活跃请求数
     *
     * @param app 应用模型
     * @return 最大活跃请求数
     */
    private int getMaxActiveRequests(App app) {
        Integer maxActiveRequests = app.getMaxActiveRequests();
        if (maxActiveRequests == null) {
            maxActiveRequests = 10; // 默认值
        }
        return maxActiveRequests;
    }
    
//    /**
//     * 获取工作流
//     *
//     * @param app 应用模型
//     * @param invokeFrom 调用来源
//     * @return 工作流
//     */
//    private Workflow getWorkflow(App app, String invokeFrom) {
//        WorkflowService workflowService = new WorkflowService();
//
//        if ("debugger".equals(invokeFrom)) {
//            // 获取草稿工作流
//            Workflow workflow = workflowService.getDraftWorkflow(app);
//            if (workflow == null) {
//                throw new AppException("Workflow not initialized");
//            }
//            return workflow;
//        } else {
//            // 获取已发布工作流
//            Workflow workflow = workflowService.getPublishedWorkflow(app);
//            if (workflow == null) {
//                throw new AppException("Workflow not published");
//            }
//            return workflow;
//        }
//    }
}