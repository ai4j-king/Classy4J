package com.classy4j.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.classy4j.model.App;
import com.classy4j.service.AppService;

@RestController
@RequestMapping("/api/apps")
public class AppController {
    @Autowired
    private AppService appService;

    @GetMapping("/listApps")
    public ResponseEntity<Page<App>> getApps(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(defaultValue = "tenantId") String tenantId,
            @RequestParam(defaultValue = "dcy") String userId) {
        Page<App> apps = appService.getPaginateApps(userId, tenantId, page, limit);
        return ResponseEntity.ok(apps);
    }

    @PostMapping("/create")
    public ResponseEntity<App> createApp(
            @RequestBody Map<String, String> payload) {
        App app = appService.createApp(
                "dcy",
                "tenantId",
                payload.get("name"),
                payload.get("description"),
                payload.get("mode"));
        return new ResponseEntity<>(app, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<App> updateApp(
            @PathVariable String id,
            @RequestBody Map<String, String> payload) {
        return appService.getApp(id)
                .map(app -> ResponseEntity.ok(
                        appService.updateApp(app,
                                payload.get("name"),
                                payload.get("description"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/icon")
    public ResponseEntity<App> updateAppIcon(
            @PathVariable String id,
            @RequestBody Map<String, String> payload) {
        return appService.getApp(id)
                .map(app -> ResponseEntity.ok(
                        appService.updateAppIcon(app,
                                payload.get("icon_type"),
                                payload.get("icon"),
                                payload.get("icon_background"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/site-enable")
    public ResponseEntity<App> updateAppSiteStatus(
            @PathVariable String id,
            @RequestBody Map<String, Boolean> payload) {
        return appService.getApp(id)
                .map(app -> ResponseEntity.ok(
                        appService.updateAppSiteStatus(app,
                                payload.get("enable_site"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/api-enable")
    public ResponseEntity<App> updateAppApiStatus(
            @PathVariable String id,
            @RequestBody Map<String, Boolean> payload) {
        return appService.getApp(id)
                .map(app -> ResponseEntity.ok(
                        appService.updateAppApiStatus(app,
                                payload.get("enable_api"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<App> getAppById(@PathVariable String id) {
        return appService.getApp(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApp(@PathVariable String id) {
        appService.deleteApp(id);
        return ResponseEntity.noContent().build();
    }
}