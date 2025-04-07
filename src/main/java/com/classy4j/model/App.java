package com.classy4j.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.classy4j.config.UUIDAttributeConverter;
import jakarta.persistence.*;

import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "apps")
@Data
public class App {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Convert(converter = UUIDAttributeConverter.class)
    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT DEFAULT ''")
    private String description;

    @Column(name = "mode", nullable = false)
    private String mode;

    @Column(name = "icon_type")
    private String iconType;

    @Column(name = "icon")
    private String icon;

    @Column(name = "icon_background")
    private String iconBackground;

    @Convert(converter = UUIDAttributeConverter.class)
    @Column(name = "app_model_config_id")
    private UUID appModelConfigId;

    @OneToOne
    @JoinColumn(name = "app_model_config_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AppModelConfig appModelConfig;

    @Convert(converter = UUIDAttributeConverter.class)
    @Column(name = "workflow_id")
    private UUID workflowId;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'normal'")
    private String status;

    @Column(name = "enable_site", nullable = false)
    private boolean enableSite;

    @Column(name = "enable_api", nullable = false)
    private boolean enableApi;

    @Column(name = "api_rpm", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer apiRpm = 0;

    @Column(name = "api_rph", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer apiRph = 0;

    @Column(name = "is_demo", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isDemo=false;

    @Column(name = "is_public", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isPublic=false;

    @Column(name = "is_universal", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isUniversal=false;

    @Column(name = "tracing")
    private String tracing;

    @Column(name = "max_active_requests")
    private Integer maxActiveRequests;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt=LocalDateTime.now();

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt=LocalDateTime.now();

    @Convert(converter = UUIDAttributeConverter.class)
    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Convert(converter = UUIDAttributeConverter.class)
    @Column(name = "updated_by")
    private UUID updatedBy;

}