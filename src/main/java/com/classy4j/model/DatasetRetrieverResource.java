
package com.classy4j.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "dataset_retriever_resources")
@Data
public class DatasetRetrieverResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "message_id", nullable = false)
    private UUID messageId;

    @Column(name = "position", nullable = false)
    private int position;

    @Column(name = "dataset_id", nullable = false)
    private UUID datasetId;

    @Column(name = "dataset_name", nullable = false, columnDefinition = "TEXT")
    private String datasetName;

    @Column(name = "document_id")
    private UUID documentId;

    @Column(name = "document_name", nullable = false, columnDefinition = "TEXT")
    private String documentName;

    @Column(name = "data_source_type", columnDefinition = "TEXT")
    private String dataSourceType;

    @Column(name = "segment_id")
    private UUID segmentId;

    @Column(name = "score")
    private Double score;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "hit_count")
    private Integer hitCount;

    @Column(name = "word_count")
    private Integer wordCount;

    @Column(name = "segment_position")
    private Integer segmentPosition;

    @Column(name = "index_node_hash", columnDefinition = "TEXT")
    private String indexNodeHash;

    @Column(name = "retriever_from", nullable = false, columnDefinition = "TEXT")
    private String retrieverFrom;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

}