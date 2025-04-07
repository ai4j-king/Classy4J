package com.classy4j.model;

import java.util.List;

import lombok.Data;

@Data
public class QuotaConfiguration {
    private String quotaType;
    private Boolean isValid;
    private List<RestrictModel> restrictModels;
}