package com.ai.classy4j.apps.chat.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import dev.langchain4j.model.chat.ChatLanguageModel;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.classy4j.apps.application.service.AppService;
import com.ai.classy4j.apps.chat.entity.ChatMessage;
import com.ai.classy4j.apps.chat.model.ChatMessageResp;
import com.ai.classy4j.apps.chat.model.ChatRequest;
import com.ai.classy4j.apps.chat.repository.ChatMessageRepository;
import com.ai.classy4j.apps.chat.service.ChatService;
import com.ai.classy4j.apps.common.BusinessException;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private AppService appService;

    @Autowired
    private ChatMessageRepository chatMessageRepository;


    @Resource
    private ChatLanguageModel chatLanguageModel;

    private ChatMessageResp convertToModel(ChatMessage entity) {
        ChatMessageResp model = new ChatMessageResp();
        model.setMessageId(entity.getMessageId());
        model.setAppId(entity.getAppId());
        model.setContent(entity.getContent());
        model.setType(entity.getType());
        model.setLanguage(entity.getLanguage());
        model.setCreateTime(entity.getCreateTime());

        ChatMessageResp.AIResponse aiResponse = new ChatMessageResp.AIResponse();
        aiResponse.setContent(entity.getAiResponseContent());
        aiResponse.setType(entity.getAiResponseType());
        model.setAiResponse(aiResponse);

        return model;
    }

    private ChatMessage convertToEntity(ChatMessageResp model) {
        ChatMessage entity = new ChatMessage();
        entity.setMessageId(model.getMessageId());
        entity.setAppId(model.getAppId());
        entity.setContent(model.getContent());
        entity.setType(model.getType());
        entity.setLanguage(model.getLanguage());
        entity.setCreateTime(model.getCreateTime());

        if (model.getAiResponse() != null) {
            entity.setAiResponseContent(model.getAiResponse().getContent());
            entity.setAiResponseType(model.getAiResponse().getType());
        }

        return entity;
    }

    @Override
    public ChatMessageResp sendMessage(String appId, ChatRequest.SendMessageRequest request) {
        // 验证应用是否存在且启用
        validateApp(appId);

        ChatMessageResp message = new ChatMessageResp();
        message.setMessageId(UUID.randomUUID().toString());
        message.setAppId(appId);
        message.setContent(request.getContent());
        message.setType(request.getType());
        message.setLanguage(request.getLanguage());
        message.setCreateTime(LocalDateTime.now());

        String respMessage = chatLanguageModel.chat(message.getContent());

        // 模拟AI响应
        ChatMessageResp.AIResponse aiResponse = new ChatMessageResp.AIResponse();
        aiResponse.setContent(respMessage);
        aiResponse.setType("text");
        message.setAiResponse(aiResponse);

        // 保存消息
        ChatMessage entity = convertToEntity(message);
        entity = chatMessageRepository.save(entity);
        return convertToModel(entity);
    }

    @Override
    public List<ChatMessageResp> getHistory(String appId, ChatRequest.HistoryRequest request) {
        validateApp(appId);

        List<ChatMessage> messages;
        if (request.getStartTime() != null && request.getEndTime() != null) {
            LocalDateTime startTime = LocalDateTime.parse(request.getStartTime());
            LocalDateTime endTime = LocalDateTime.parse(request.getEndTime());
            messages = chatMessageRepository.findByAppIdAndCreateTimeBetweenOrderByCreateTimeDesc(
                appId, startTime, endTime);
        } else if (request.getKeyword() != null) {
            messages = chatMessageRepository.findByAppIdAndContentContainingOrderByCreateTimeDesc(
                appId, request.getKeyword());
        } else {
            messages = chatMessageRepository.findByAppId(appId);
        }

        // 分页处理
        int start = (request.getPage() - 1) * request.getPageSize();
        int end = Math.min(start + request.getPageSize(), messages.size());
        messages = messages.subList(start, end);
        
        return messages.stream()
            .map(this::convertToModel)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteMessage(String appId, String messageId) {
        validateApp(appId);

        chatMessageRepository.deleteById(messageId);
    }

    @Override
    public byte[] exportHistory(String appId, ChatRequest.ExportRequest request) {
        validateApp(appId);

        List<ChatMessage> filteredMessages;
        if (request.getStartTime() != null && request.getEndTime() != null) {
            LocalDateTime startTime = LocalDateTime.parse(request.getStartTime());
            LocalDateTime endTime = LocalDateTime.parse(request.getEndTime());
            filteredMessages = chatMessageRepository.findByAppIdAndCreateTimeBetweenOrderByCreateTimeDesc(
                appId, startTime, endTime);
        } else {
            filteredMessages = chatMessageRepository.findByAppId(appId);
        }

        // 根据格式导出
        if ("markdown".equals(request.getFormat())) {
            return exportToMarkdown(filteredMessages);
        } else {
            return exportToJson(filteredMessages);
        }
    }

    private void validateApp(String appId) {
        var app = appService.getAppById(appId);
        if (app == null) {
            throw new BusinessException(404, "应用不存在");
        }
        if (!"enabled".equals(app.getStatus())) {
            throw new BusinessException(400, "应用已禁用");
        }
    }

    private byte[] exportToMarkdown(List<ChatMessage> messages) {
        StringBuilder sb = new StringBuilder();
        sb.append("# 对话历史记录\n\n");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (ChatMessage message : messages) {
            sb.append("## ").append(message.getCreateTime().format(formatter)).append("\n\n");
            sb.append("**用户消息**\n\n");
            if ("code".equals(message.getType())) {
                sb.append("```").append(message.getLanguage()).append("\n");
                sb.append(message.getContent()).append("\n```\n\n");
            } else {
                sb.append(message.getContent()).append("\n\n");
            }
            sb.append("**AI响应**\n\n");
            sb.append(message.getAiResponseContent()).append("\n\n");
        }

        return sb.toString().getBytes();
    }

    private byte[] exportToJson(List<ChatMessage> messages) {
        // 简单实现，实际项目中应使用JSON库
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < messages.size(); i++) {
            ChatMessage message = messages.get(i);
            sb.append("  {\n");
            sb.append("    \"messageId\": \"").append(message.getMessageId()).append("\",\n");
            sb.append("    \"content\": \"").append(message.getContent()).append("\",\n");
            sb.append("    \"type\": \"").append(message.getType()).append("\",\n");
            sb.append("    \"createTime\": \"").append(message.getCreateTime()).append("\",\n");
            sb.append("    \"aiResponse\": {\n");
            sb.append("      \"content\": \"").append(message.getAiResponseContent()).append("\",\n");
            sb.append("      \"type\": \"").append(message.getAiResponseType()).append("\"\n");
            sb.append("    }\n");
            sb.append("  }").append(i < messages.size() - 1 ? "," : "").append("\n");
        }
        sb.append("]\n");

        return sb.toString().getBytes();
    }
}