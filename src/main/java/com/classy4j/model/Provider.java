package com.classy4j.model;

import java.time.LocalDateTime;

import com.classy4j.config.LLMConfig;
import com.classy4j.config.ObjectToJsonConverter;
import com.classy4j.entity.bo.ModelConfigReq;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@Entity
@Table(name = "providers")
public class Provider {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @Column(name = "provider_name", nullable = false)
    private String providerName;

    @Column(name = "provider_type", nullable = false)
    private String providerType = "custom";

    @Column(name = "encrypted_config")
    @JdbcTypeCode(SqlTypes.JSON)
    private LLMConfig  encryptedConfig;

    @Column(name = "is_valid", nullable = false)
    private Boolean isValid = false;

    @Column(name = "last_used")
    private LocalDateTime lastUsed;

    @Column(name = "quota_type")
    private String quotaType = "";

    @Column(name = "quota_limit")
    private Long quotaLimit;

    @Column(name = "quota_used")
    private Long quotaUsed = 0L;

    @Column(name = "created_at", nullable = false, updatable = false)
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

    public boolean isEnabled() {
        if ("SYSTEM".equals(providerType)) {
            return isValid;
        } else {
            return isValid && encryptedConfig != null;
        }
    }
}