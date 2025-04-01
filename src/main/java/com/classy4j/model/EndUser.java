package com.classy4j.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 终端用户实体类
 */
@Data
@Entity
@Table(name = "end_users")
@EqualsAndHashCode(callSuper = false)
public class EndUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(name = "tenant_id", nullable = false, length = 32)
    private String tenantId;
    
    @Column(name = "app_id", length = 32)
    private String appId;
    
    @Column(nullable = false)
    private String type;
    
    @Column(name = "external_user_id")
    private String externalUserId;
    
    private String name;
    
    @Column(name = "is_anonymous", nullable = false)
    private Boolean isAnonymous = true;
    
    @Column(name = "session_id", nullable = false)
    private String sessionId;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}