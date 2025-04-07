package com.classy4j.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.classy4j.config.ObjectToJsonConverter;
import com.classy4j.entity.bo.ModelConfigReq;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "app_model_configs")
@Data
public class AppModelConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "app_id", nullable = false,columnDefinition = "uuid")
    private UUID appId;

    @Column(name = "provider")
    private String provider;

    @Column(name = "model_id")
    private String modelId;

    @Column(name = "opening_statement", columnDefinition = "TEXT")
    private String openingStatement;

    @Column(name = "suggested_questions", columnDefinition = "TEXT")
    private String suggestedQuestions;

    @Column(name = "suggested_questions_after_answer", columnDefinition = "TEXT")
    @JdbcTypeCode(SqlTypes.JSON)
    private ModelConfigReq.SuggestedQuestionsAfterAnswer suggestedQuestionsAfterAnswer;


    @Column(name = "speech_to_text", columnDefinition = "TEXT")
     @JdbcTypeCode(SqlTypes.JSON)
    private ModelConfigReq.SpeechToText speechToText;

    @Column(name = "text_to_speech", columnDefinition = "TEXT")
    @JdbcTypeCode(SqlTypes.JSON)
    private ModelConfigReq.TextToSpeech textToSpeech;

    @Column(name = "more_like_this", columnDefinition = "TEXT")
    private String moreLikeThis;

    @Column(name = "model", columnDefinition = "TEXT")
     @JdbcTypeCode(SqlTypes.JSON)
    private ModelConfigReq.Model model;

    @Column(name = "user_input_form", columnDefinition = "TEXT")
    private String userInputForm;

    @Column(name = "dataset_query_variable")
    private String datasetQueryVariable;

    @Column(name = "pre_prompt", columnDefinition = "TEXT")
    private String prePrompt;

    @Column(name = "agent_mode", columnDefinition = "TEXT")
     @JdbcTypeCode(SqlTypes.JSON)
    private ModelConfigReq.AgentMode agentMode;

    @Column(name = "sensitive_word_avoidance", columnDefinition = "TEXT")
     @JdbcTypeCode(SqlTypes.JSON)
    private ModelConfigReq.SensitiveWordAvoidance sensitiveWordAvoidance;

    @Column(name = "retriever_resource", columnDefinition = "TEXT")
     @JdbcTypeCode(SqlTypes.JSON)
    private ModelConfigReq.RetrieverResource retrieverResource;

    @Column(name = "prompt_type", nullable = false)
    private String promptType = "simple";

    @Column(name = "chat_prompt_config", columnDefinition = "TEXT")
    private String chatPromptConfig;

    @Column(name = "completion_prompt_config", columnDefinition = "TEXT")
    private String completionPromptConfig;

    @Column(name = "dataset_configs", columnDefinition = "TEXT")
     @JdbcTypeCode(SqlTypes.JSON)
    private ModelConfigReq.DatasetConfigs datasetConfigs;

    @Column(name = "external_data_tools", columnDefinition = "TEXT")
    private String externalDataTools;

    @Column(name = "file_upload", columnDefinition = "TEXT")
     @JdbcTypeCode(SqlTypes.JSON)
    private ModelConfigReq.FileUpload fileUpload;

    @Column(name = "created_by")
    private UUID createdBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_by")
    private UUID updatedBy;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}