package com.classy4j.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.classy4j.repository.AppRepository;
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

    @Autowired
    private AppRepository appRepository;

    private static UUID defTenantId = UUID.fromString("70a70885-be37-49e5-92f6-0fd221e8175b");
    private static UUID defUserId = UUID.fromString("3fbbacdd-28ba-4e05-a29a-89fd5cfbea5f");

    @GetMapping("/listApps")
    public ResponseEntity<Page<App>> getApps(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(required = false) UUID tenantId,
            @RequestParam(required = false) UUID userId) {
        if (tenantId == null){
            tenantId = defTenantId;
        }
        if (userId == null){
            userId = defUserId;
        }
        Page<App> apps = appService.getPaginateApps(userId, tenantId, page, limit);
        return ResponseEntity.ok(apps);
    }

    @PostMapping("/create")
    public ResponseEntity<App> createApp(
            @RequestBody Map<String, String> payload) {
        App app = appService.createApp(
                defUserId,
                defTenantId,
                payload.get("name"),
                payload.get("description"),
                payload.get("mode"));
        return new ResponseEntity<>(app, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<App> updateApp(
            @PathVariable UUID id,
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
            @PathVariable UUID id,
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
            @PathVariable UUID id,
            @RequestBody Map<String, Boolean> payload) {
        return appService.getApp(id)
                .map(app -> ResponseEntity.ok(
                        appService.updateAppSiteStatus(app,
                                payload.get("enable_site"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/api-enable")
    public ResponseEntity<App> updateAppApiStatus(
            @PathVariable UUID id,
            @RequestBody Map<String, Boolean> payload) {
        return appService.getApp(id)
                .map(app -> ResponseEntity.ok(
                        appService.updateAppApiStatus(app,
                                payload.get("enable_api"))))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<App> getAppById(@PathVariable UUID id) {
        return appService.getApp(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApp(@PathVariable UUID id) {
        appService.deleteApp(id);
        return ResponseEntity.noContent().build();
    }
}