package com.ai.classy4j.apps.application.service;

import java.util.List;

import com.ai.classy4j.apps.application.model.AppInfo;
import com.ai.classy4j.apps.application.model.AppRequest;

public interface AppService {
    AppInfo createApp(AppRequest.CreateAppRequest request);
    
    void updateAppConfig(String appId, AppRequest.UpdateAppConfigRequest request);
    
    void updateAppStatus(String appId, AppRequest.UpdateAppStatusRequest request);
    
    void deleteApp(String appId);
    
    List<AppInfo> listApps(AppRequest.PageRequest request);
    
    AppInfo getAppById(String appId);
}