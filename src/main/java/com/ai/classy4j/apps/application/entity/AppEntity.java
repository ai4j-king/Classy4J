package com.ai.classy4j.apps.application.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.ai.classy4j.apps.application.entity.converter.AppConfigConverter;
import com.ai.classy4j.apps.application.entity.converter.JsonListConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.ai.classy4j.apps.application.model.AppInfo;

import lombok.Data;

@Data
@Entity
@Table(name = "app_info")
@Slf4j
public class AppEntity {
    @Id
    private String appId;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(nullable = false)
    private String type;
    
    @Column(length = 1000)
    private String description;
    
    @Convert(converter = JsonListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> tags;
    
    @Column(nullable = false)
    private String status;
    
    @Column(nullable = false)
    private LocalDateTime createTime;
    
    @Column(nullable = false)
    private LocalDateTime updateTime;
    
    @Convert(converter = AppConfigConverter.class)
    @Column(columnDefinition = "TEXT")
    private AppInfo.AppConfig appConfig;
    
    public AppInfo toModel() {
        log.info("convert to model: {}", this);
        AppInfo appInfo = new AppInfo();
        appInfo.setAppId(this.appId);
        appInfo.setName(this.name);
        appInfo.setType(this.type);
        appInfo.setDescription(this.description);
        appInfo.setTags(this.tags);
        appInfo.setStatus(this.status);
        appInfo.setCreateTime(this.createTime);
        appInfo.setUpdateTime(this.updateTime);
        appInfo.setAppConfig(this.appConfig);
        return appInfo;
    }
    
    public static AppEntity fromModel(AppInfo appInfo) {
        AppEntity entity = new AppEntity();
        entity.setAppId(appInfo.getAppId());
        entity.setName(appInfo.getName());
        entity.setType(appInfo.getType());
        entity.setDescription(appInfo.getDescription());
        entity.setTags(appInfo.getTags());
        entity.setStatus(appInfo.getStatus());
        entity.setCreateTime(appInfo.getCreateTime());
        entity.setUpdateTime(appInfo.getUpdateTime());
        return entity;
    }
}