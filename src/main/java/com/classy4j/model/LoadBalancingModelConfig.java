package com.classy4j.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "load_balancing_model_configs")
public class LoadBalancingModelConfig {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @Column(name = "provider_name", nullable = false)
    private String providerName;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @Column(name = "model_type", nullable = false)
    private String modelType;

    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled = false;

    @Column(name = "config")
    private String config;

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
}