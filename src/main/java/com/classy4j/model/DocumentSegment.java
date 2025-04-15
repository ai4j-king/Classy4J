
package com.classy4j.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "document_segments", schema = "public")
@Data
public class DocumentSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "dataset_id", nullable = false)
    private UUID datasetId;

    @Column(name = "document_id", nullable = false)
    private UUID documentId;

    @Column(name = "position", nullable = false)
    private int position;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "word_count", nullable = false)
    private int wordCount;

    @Column(name = "tokens", nullable = false)
    private int tokens;

    @Column(name = "keywords", columnDefinition = "json")
    private String keywords;

    @Column(name = "index_node_id", length = 255)
    private String indexNodeId;

    @Column(name = "index_node_hash", length = 255)
    private String indexNodeHash;

    @Column(name = "hit_count", nullable = false)
    private int hitCount;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name = "disabled_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date disabledAt;

    @Column(name = "disabled_by")
    private UUID disabledBy;

    @Column(name = "status", nullable = false, length = 255)
    private String status = "waiting";

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "indexing_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date indexingAt;

    @Column(name = "completed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completedAt;

    @Column(name = "error", columnDefinition = "TEXT")
    private String error;

    @Column(name = "stopped_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stoppedAt;

    @Column(name = "answer", columnDefinition = "TEXT")
    private String answer;

    @Column(name = "updated_by")
    private UUID updatedBy;

    @Column(name = "updated_at", nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENTESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}