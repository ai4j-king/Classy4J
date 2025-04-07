package com.classy4j.model;

import com.classy4j.core.model_manager.model.ModelType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "provider_models")
public class ProviderModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "provider_id", nullable = false)
    private String providerId;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @Column(name = "model_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ModelType modelType;

    @Column(name = "model_label")
    private String modelLabel;

    @Column(name = "is_valid", nullable = false)
    private Boolean isValid = true;

    @Column(name = "is_deprecated", nullable = false)
    private Boolean isDeprecated = false;

    @Column(name = "model_properties")
    private String modelProperties;

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