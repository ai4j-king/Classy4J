package com.classy4j.system;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ModelProvider {
    private String background;
    
    @JsonProperty("configurate_methods")
    private List<String> configurateMethods;
    
    private Label description;
    private Extra extra;
    private Help help;
    
    @JsonProperty("icon_large")
    private Map<String, String> iconLarge;
    
    @JsonProperty("icon_small")
    private Map<String, String> iconSmall;
    
    private Label label;
    private Models models;
    private String provider;
    
    @JsonProperty("provider_credential_schema")
    private ProviderCredentialSchema providerCredentialSchema;
    
    @JsonProperty("supported_model_types")
    private List<String> supportedModelTypes;

    @Data
    public static class Extra {
        private Python python;
        
        @Data
        public static class Python {
            @JsonProperty("model_sources")
            private List<String> modelSources;
            
            @JsonProperty("provider_source")
            private String providerSource;
        }
    }
    
    @Data
    public static class Help {
        private Label title;
        private Url url;

        @Data
        public static class Url {
            @JsonProperty("en_US")
            private String enUS;
        }
    }
    
    @Data
    public static class Models {
        private LLM llm;
        
        @Data
        public static class LLM {
            private String position;
            private List<String> predefined;
        }
    }
    
    @Data
    public static class ProviderCredentialSchema {
        @JsonProperty("credential_form_schemas")
        private List<CredentialFormSchema> credentialFormSchemas;
        
        @Data
        public static class CredentialFormSchema {
            private Map<String, String> label;
            private Map<String, String> placeholder;
            private boolean required;
            private String type;
            private String variable;
        }
    }
}