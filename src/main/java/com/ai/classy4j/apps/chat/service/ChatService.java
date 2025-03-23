package com.ai.classy4j.apps.chat.service;

import java.util.List;

import com.ai.classy4j.apps.chat.model.ChatMessageResp;
import com.ai.classy4j.apps.chat.model.ChatRequest;

public interface ChatService {
    ChatMessageResp sendMessage(String appId, ChatRequest.SendMessageRequest request);
    
    List<ChatMessageResp> getHistory(String appId, ChatRequest.HistoryRequest request);
    
    void deleteMessage(String appId, String messageId);
    
    byte[] exportHistory(String appId, ChatRequest.ExportRequest request);
}