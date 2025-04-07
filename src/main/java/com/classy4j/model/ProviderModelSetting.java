package com.classy4j.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "provider_model_settings")
public class ProviderModelSetting {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "provider_model_id", nullable = false)
    private String providerModelId;

    @Column(name = "setting_key", nullable = false)
    private String settingKey;

    @Column(name = "setting_value")
    private String settingValue;

    @Column(name = "is_valid", nullable = false)
    private Boolean isValid = true;

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