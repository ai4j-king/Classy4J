package com.ai.classy4j.apps.chat.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ai.classy4j.apps.chat.entity.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {
    List<ChatMessage> findByAppId(String appId);
    
    List<ChatMessage> findByAppIdAndCreateTimeBetweenOrderByCreateTimeDesc(
        String appId, 
        LocalDateTime startTime, 
        LocalDateTime endTime
    );
    
    List<ChatMessage> findByAppIdAndContentContainingOrderByCreateTimeDesc(
        String appId, 
        String keyword
    );
}