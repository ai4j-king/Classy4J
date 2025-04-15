
package com.classy4j.controller;

import com.classy4j.model.Document;
import com.classy4j.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService service;

    @GetMapping
    public List<Document> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getById(@PathVariable UUID id) {
        Optional<Document> document = service.findById(id);
        return document.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/tenant/{tenantId}")
    public List<Document> getByTenantId(@PathVariable UUID tenantId) {
        return service.findByTenantId(tenantId);
    }

    @GetMapping("/dataset/{datasetId}")
    public List<Document> getByDatasetId(@PathVariable UUID datasetId) {
        return service.findByDatasetId(datasetId);
    }

    @GetMapping("/batch/{batch}")
    public List<Document> getByBatch(@PathVariable String batch) {
        return service.findByBatch(batch);
    }

    @GetMapping("/paused/{isPaused}")
    public List<Document> getByIsPaused(@PathVariable boolean isPaused) {
        return service.findByIsPaused(isPaused);
    }

    @GetMapping("/enabled/{enabled}")
    public List<Document> getByEnabled(@PathVariable boolean enabled) {
        return service.findByEnabled(enabled);
    }

    @GetMapping("/archived/{archived}")
    public List<Document> getByArchived(@PathVariable boolean archived) {
        return service.findByArchived(archived);
    }

    @PostMapping
    public Document create(@RequestBody Document document) {
        return service.save(document);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}