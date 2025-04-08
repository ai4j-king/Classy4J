package com.classy4j.entity.bo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ModelConfigReq {
    @JsonProperty("pre_prompt")
    private String prePrompt;

    @JsonProperty("prompt_type")
    private String promptType;

    @JsonProperty("chat_prompt_config")
    private ChatPromptConfig chatPromptConfig;

    @JsonProperty("completion_prompt_config")
    private CompletionPromptConfig completionPromptConfig;

    @JsonProperty("user_input_form")
    private List<Object> userInputForm;

    @JsonProperty("dataset_query_variable")
    private String datasetQueryVariable;

    @JsonProperty("more_like_this")
    private MoreLikeThis moreLikeThis;

    @JsonProperty("opening_statement")
    private String openingStatement;

    @JsonProperty("suggested_questions")
    private List<String> suggestedQuestions;

    @JsonProperty("sensitive_word_avoidance")
    private SensitiveWordAvoidance sensitiveWordAvoidance;

    @JsonProperty("speech_to_text")
    private SpeechToText speechToText;

    @JsonProperty("text_to_speech")
    private TextToSpeech textToSpeech;

    @JsonProperty("file_upload")
    private FileUpload fileUpload;

    @JsonProperty("suggested_questions_after_answer")
    private SuggestedQuestionsAfterAnswer suggestedQuestionsAfterAnswer;

    @JsonProperty("retriever_resource")
    private RetrieverResource retrieverResource;

    @JsonProperty("agent_mode")
    private AgentMode agentMode;

    @JsonProperty("model")
    private Model model;

    @JsonProperty("dataset_configs")
    private DatasetConfigs datasetConfigs;

    @Data
    public static class ChatPromptConfig {}

    @Data
    public static class CompletionPromptConfig {}

    @Data
    public static class MoreLikeThis {
        private boolean enabled;
    }

    @Data
    public static class SensitiveWordAvoidance {
        private boolean enabled;
        private String type;
        private List<Object> configs;
    }

    @Data
    public static class SpeechToText {
        private boolean enabled;
    }

    @Data
    public static class TextToSpeech {
        private boolean enabled;
        private String voice;
        private String language;
    }

    @Data
    public static class FileUpload {
        private ImageConfig image;
        private boolean enabled;

        @JsonProperty("allowed_file_types")
        private List<String> allowedFileTypes;

        @JsonProperty("allowed_file_extensions")
        private List<String> allowedFileExtensions;

        @JsonProperty("allowed_file_upload_methods")
        private List<String> allowedFileUploadMethods;

        @JsonProperty("number_limits")
        private int numberLimits;

        @Data
        public static class ImageConfig {
            private String detail;
            private boolean enabled;

            @JsonProperty("number_limits")
            private int numberLimits;

            @JsonProperty("transfer_methods")
            private List<String> transferMethods;
        }
    }

    @Data
    public static class SuggestedQuestionsAfterAnswer {
        private boolean enabled;
    }

    @Data
    public static class RetrieverResource {
        private boolean enabled;
    }

    @Data
    public static class AgentMode {
        private boolean enabled;

        @JsonProperty("max_iteration")
        private int maxIteration;

        private String strategy;

        private String prompt;

        private List<Object> tools;
    }

    @Data
    public static class Model {
        private String provider;
        private String name;
        private String mode;

        @JsonProperty("completion_params")
        private CompletionParams completionParams;

        @Data
        public static class CompletionParams {
            private List<String> stop;
        }
    }

    @Data
    public static class DatasetConfigs {
        @JsonProperty("retrieval_model")
        private String retrievalModel;

        @JsonProperty("top_k")
        private int topK;

        @JsonProperty("reranking_enable")
        private boolean rerankingEnable;

        private DatasetList datasets;

        @Data
        public static class DatasetList {
            private List<Object> datasets;
        }
    }
}