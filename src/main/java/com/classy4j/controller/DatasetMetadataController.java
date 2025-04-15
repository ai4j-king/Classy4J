
package com.classy4j.controller;

import com.classy4j.model.DatasetMetadata;
import com.classy4j.service.DatasetMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/dataset-metadatas")
public class DatasetMetadataController {

    @Autowired
    private DatasetMetadataService service;

    @GetMapping
    public List<DatasetMetadata> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatasetMetadata> getById(@PathVariable UUID id) {
        Optional<DatasetMetadata> metadata = service.findById(id);
        return metadata.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/dataset/{datasetId}")
    public List<DatasetMetadata> getByDatasetId(@PathVariable UUID datasetId) {
        return service.findByDatasetId(datasetId);
    }

    @PostMapping
    public DatasetMetadata create(@RequestBody DatasetMetadata metadata) {
        return service.save(metadata);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}