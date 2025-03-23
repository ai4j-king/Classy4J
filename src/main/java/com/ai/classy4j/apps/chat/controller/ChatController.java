package com.ai.classy4j.apps.chat.controller;

import java.util.List;

import dev.langchain4j.model.chat.ChatLanguageModel;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai.classy4j.apps.chat.model.ChatMessageResp;
import com.ai.classy4j.apps.chat.model.ChatRequest;
import com.ai.classy4j.apps.chat.service.ChatService;
import com.ai.classy4j.apps.common.ApiResponse;

@RestController
@RequestMapping("/api/v1/apps/{appId}/chat")
public class ChatController {
    
    @Autowired
    private ChatService chatService;

    @PostMapping("/messages")
    public ApiResponse<ChatMessageResp> sendMessage(
            @PathVariable String appId,
            @RequestBody ChatRequest.SendMessageRequest request) {
        return ApiResponse.success(chatService.sendMessage(appId, request));
    }

    @GetMapping("/messages")
    public ApiResponse<List<ChatMessageResp>> getHistory(
            @PathVariable String appId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int pageSize,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) String keyword) {
        ChatRequest.HistoryRequest request = new ChatRequest.HistoryRequest();
        request.setPage(page);
        request.setPageSize(pageSize);
        request.setStartTime(startTime);
        request.setEndTime(endTime);
        request.setKeyword(keyword);
        return ApiResponse.success(chatService.getHistory(appId, request));
    }

    @DeleteMapping("/messages/{messageId}")
    public ApiResponse<Void> deleteMessage(
            @PathVariable String appId,
            @PathVariable String messageId) {
        chatService.deleteMessage(appId, messageId);
        return ApiResponse.success();
    }

    @GetMapping("/messages/export")
    public ResponseEntity<byte[]> exportHistory(
            @PathVariable String appId,
            @RequestParam String startTime,
            @RequestParam String endTime,
            @RequestParam(defaultValue = "markdown") String format) {
        ChatRequest.ExportRequest request = new ChatRequest.ExportRequest();
        request.setStartTime(startTime);
        request.setEndTime(endTime);
        request.setFormat(format);

        byte[] data = chatService.exportHistory(appId, request);
        String filename = "chat_history." + ("markdown".equals(format) ? "md" : "json");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", filename);

        return ResponseEntity.ok()
                .headers(headers)
                .body(data);
    }
}