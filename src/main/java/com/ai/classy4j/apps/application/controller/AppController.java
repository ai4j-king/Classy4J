package com.ai.classy4j.apps.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai.classy4j.apps.application.model.AppInfo;
import com.ai.classy4j.apps.application.model.AppRequest;
import com.ai.classy4j.apps.application.service.AppService;
import com.ai.classy4j.apps.common.ApiResponse;

@RestController
@RequestMapping("/api/v1/apps")
public class AppController {
    
    @Autowired
    private AppService appService;

    @PostMapping("/create")
    public ApiResponse<AppInfo> createApp(@RequestBody AppRequest.CreateAppRequest request) {
        return ApiResponse.success(appService.createApp(request));
    }

    @PutMapping("/{appId}/config")
    public ApiResponse<Void> updateAppConfig(
            @PathVariable String appId,
            @RequestBody AppRequest.UpdateAppConfigRequest request) {
        appService.updateAppConfig(appId, request);
        return ApiResponse.success();
    }

    @PutMapping("/{appId}/status")
    public ApiResponse<Void> updateAppStatus(
            @PathVariable String appId,
            @RequestBody AppRequest.UpdateAppStatusRequest request) {
        appService.updateAppStatus(appId, request);
        return ApiResponse.success();
    }

    @DeleteMapping("/{appId}")
    public ApiResponse<Void> deleteApp(@PathVariable String appId) {
        appService.deleteApp(appId);
        return ApiResponse.success();
    }

    @GetMapping("/listApps")
    public ApiResponse<List<AppInfo>> listApps(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String status) {
        AppRequest.PageRequest request = new AppRequest.PageRequest();
        request.setPage(page);
        request.setPageSize(pageSize);
        request.setKeyword(keyword);
        request.setType(type);
        request.setStatus(status);
        return ApiResponse.success(appService.listApps(request));
    }

    @GetMapping("/{appId}")
    public ApiResponse<AppInfo> getAppById(@PathVariable String appId) {
        return ApiResponse.success(appService.getAppById(appId));
    }
}