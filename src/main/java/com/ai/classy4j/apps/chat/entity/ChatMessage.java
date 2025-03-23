package com.ai.classy4j.apps.chat.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "chat_messages")
public class ChatMessage {
    @Id
    private String messageId;
    
    @Column(nullable = false)
    private String appId;
    
    @Column(columnDefinition = "TEXT")
    private String content;
    
    @Column(length = 50)
    private String type;
    
    @Column(length = 50)
    private String language;
    
    private LocalDateTime createTime;
    
    @Column(columnDefinition = "TEXT")
    private String aiResponseContent;
    
    @Column(length = 50)
    private String aiResponseType;
}