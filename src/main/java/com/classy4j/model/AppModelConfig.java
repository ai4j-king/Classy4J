package com.classy4j.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "app_model_configs")
@Data
public class AppModelConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "app_id", nullable = false)
    private String appId;

    @Column(name = "provider")
    private String provider;

    @Column(name = "model_id")
    private String modelId;

    @Column(name = "opening_statement", columnDefinition = "TEXT")
    private String openingStatement;

    @Column(name = "suggested_questions", columnDefinition = "TEXT")
    private String suggestedQuestions;

    @Column(name = "suggested_questions_after_answer", columnDefinition = "TEXT")
    private String suggestedQuestionsAfterAnswer;

    @Column(name = "speech_to_text", columnDefinition = "TEXT")
    private String speechToText;

    @Column(name = "text_to_speech", columnDefinition = "TEXT")
    private String textToSpeech;

    @Column(name = "more_like_this", columnDefinition = "TEXT")
    private String moreLikeThis;

    @Column(name = "model", columnDefinition = "TEXT")
    private String model;

    @Column(name = "user_input_form", columnDefinition = "TEXT")
    private String userInputForm;

    @Column(name = "dataset_query_variable")
    private String datasetQueryVariable;

    @Column(name = "pre_prompt", columnDefinition = "TEXT")
    private String prePrompt;

    @Column(name = "agent_mode", columnDefinition = "TEXT")
    private String agentMode;

    @Column(name = "sensitive_word_avoidance", columnDefinition = "TEXT")
    private String sensitiveWordAvoidance;

    @Column(name = "retriever_resource", columnDefinition = "TEXT")
    private String retrieverResource;

    @Column(name = "prompt_type", nullable = false)
    private String promptType = "simple";

    @Column(name = "chat_prompt_config", columnDefinition = "TEXT")
    private String chatPromptConfig;

    @Column(name = "completion_prompt_config", columnDefinition = "TEXT")
    private String completionPromptConfig;

    @Column(name = "dataset_configs", columnDefinition = "TEXT")
    private String datasetConfigs;

    @Column(name = "external_data_tools", columnDefinition = "TEXT")
    private String externalDataTools;

    @Column(name = "file_upload", columnDefinition = "TEXT")
    private String fileUpload;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

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