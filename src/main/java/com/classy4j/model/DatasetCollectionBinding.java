package com.classy4j.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "dataset_collection_bindings")
@Data
public class DatasetCollectionBinding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "provider_name", nullable = false)
    private String providerName;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @Column(name = "collection_name", nullable = false, length = 64)
    private String collectionName;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private java.sql.Timestamp createdAt;

    @Column(name = "type", nullable = false, length = 40)
    private String type = "dataset";


}