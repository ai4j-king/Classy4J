package com.classy4j.core.model_manager.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DefaultModelEntity extends ModelEntity {
    private String providerName;
    private String providerType;
    private String modelCredentials;
    private String globalCredentials;
}