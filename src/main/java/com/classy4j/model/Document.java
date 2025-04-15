
package com.classy4j.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "documents")
@Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "dataset_id", nullable = false)
    private UUID datasetId;

    @Column(name = "position", nullable = false)
    private int position;

    @Column(name = "data_source_type", nullable = false, length = 255)
    private String dataSourceType;

    @Column(name = "data_source_info", columnDefinition = "TEXT")
    private String dataSourceInfo;

    @Column(name = "dataset_process_rule_id")
    private UUID datasetProcessRuleId;

    @Column(name = "batch", nullable = false, length = 255)
    private String batch;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "created_from", nullable = false, length = 255)
    private String createdFrom;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "created_api_request_id")
    private UUID createdApiRequestId;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "processing_started_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date processingStartedAt;

    @Column(name = "file_id")
    private String fileId;

    @Column(name = "word_count")
    private Integer wordCount;

    @Column(name = "parsing_completed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date parsingCompletedAt;

    @Column(name = "cleaning_completed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cleaningCompletedAt;

    @Column(name = "splitting_completed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date splittingCompletedAt;

    @Column(name = "tokens")
    private Integer tokens;

    @Column(name = "indexing_latency")
    private Double indexingLatency;

    @Column(name = "completed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completedAt;

    @Column(name = "is_paused", nullable = false)
    private boolean isPaused = false;

    @Column(name = "paused_by")
    private UUID pausedBy;

    @Column(name = "paused_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pausedAt;

    @Column(name = "error", columnDefinition = "TEXT")
    private String error;

    @Column(name = "stopped_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stoppedAt;

    @Column(name = "indexing_status", nullable = false, length = 255)
    private String indexingStatus = "waiting";

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name = "disabled_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date disabledAt;

    @Column(name = "disabled_by")
    private UUID disabledBy;

    @Column(name = "archived", nullable = false)
    private boolean archived = false;

    @Column(name = "archived_reason", length = 255)
    private String archivedReason;

    @Column(name = "archived_by")
    private UUID archivedBy;

    @Column(name = "archived_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date archivedAt;

    @Column(name = "updated_at", nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "doc_type", length = 40)
    private String docType;

    @Column(name = "doc_metadata", columnDefinition = "jsonb")
    private String docMetadata;

    @Column(name = "doc_form", nullable = false, length = 255)
    private String docForm = "text_model";

    @Column(name = "doc_language", length = 255)
    private String docLanguage;

}