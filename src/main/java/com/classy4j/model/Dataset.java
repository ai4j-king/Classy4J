package com.classy4j.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "datasets")
@Data
public class Dataset {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tenant_id", columnDefinition = "uuid", nullable = false)
    private UUID tenantId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "provider", nullable = false, length = 255)
    private String provider;

    @Column(name = "permission", nullable = false, length = 255)
    private String permission;

    @Column(name = "data_source_type", length = 255)
    private String dataSourceType;

    @Column(name = "indexing_technique", length = 255)
    private String indexingTechnique;

    @Column(name = "index_struct", columnDefinition = "text")
    private String indexStruct;

    @Column(name = "created_by", columnDefinition = "uuid", nullable = false)
    private UUID createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt;

    @Column(name = "updated_by", columnDefinition = "uuid")
    private UUID updatedBy;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updatedAt;

    @Column(name = "embedding_model", length = 255)
    private String embeddingModel;

    @Column(name = "embedding_model_provider", length = 255)
    private String embeddingModelProvider;

    @Column(name = "collection_binding_id", columnDefinition = "uuid")
    private UUID collectionBindingId;

    @Column(name = "retrieval_model", columnDefinition = "jsonb")
    private String retrievalModel;

    @Column(name = "built_in_field_enabled", nullable = false)
    private boolean builtInFieldEnabled;

}

