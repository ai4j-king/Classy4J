package com.classy4j.controller;

import com.classy4j.model.Dataset;
import com.classy4j.service.DatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/datasets")
public class DatasetController {

    @Autowired
    private DatasetService datasetService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllDatasets(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(defaultValue = "true") boolean includeAll) {

        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Dataset> datasetsPage = datasetService.findAll(pageable);

        List<Dataset> datasets = datasetsPage.getContent();
        List<Map<String, Object>> result = datasetService.getExtendedDatasetInfo(datasets, includeAll);

        Map<String, Object> response = new HashMap<>();
        response.put("datasets", result);
        response.put("currentPage", datasetsPage.getNumber() + 1);
        response.put("totalItems", datasetsPage.getTotalElements());
        response.put("totalPages", datasetsPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dataset> getDatasetById(@PathVariable(value = "id") UUID id) {
        Dataset dataset = datasetService.getDatasetById(id)
                .orElseThrow(() -> new RuntimeException("Dataset not found for this id :: " + id));
        return ResponseEntity.ok().body(dataset);
    }

    @GetMapping("/tenant/{tenantId}")
    public List<Dataset> getDatasetsByTenantId(@PathVariable(value = "tenantId") UUID tenantId) {
        return datasetService.getDatasetsByTenantId(tenantId);
    }

    @GetMapping("/name/{name}")
    public List<Dataset> getDatasetsByName(@PathVariable(value = "name") String name) {
        return datasetService.getDatasetsByName(name);
    }

    @GetMapping("/provider/{provider}")
    public List<Dataset> getDatasetsByProvider(@PathVariable(value = "provider") String provider) {
        return datasetService.getDatasetsByProvider(provider);
    }

    @GetMapping("/permission/{permission}")
    public List<Dataset> getDatasetsByPermission(@PathVariable(value = "permission") String permission) {
        return datasetService.getDatasetsByPermission(permission);
    }

    @GetMapping("/dataSourceType/{dataSourceType}")
    public List<Dataset> getDatasetsByDataSourceType(@PathVariable(value = "dataSourceType") String dataSourceType) {
        return datasetService.getDatasetsByDataSourceType(dataSourceType);
    }

    @GetMapping("/indexingTechnique/{indexingTechnique}")
    public List<Dataset> getDatasetsByIndexingTechnique(@PathVariable(value = "indexingTechnique") String indexingTechnique) {
        return datasetService.getDatasetsByIndexingTechnique(indexingTechnique);
    }

    @GetMapping("/embeddingModel/{embeddingModel}")
    public List<Dataset> getDatasetsByEmbeddingModel(@PathVariable(value = "embeddingModel") String embeddingModel) {
        return datasetService.getDatasetsByEmbeddingModel(embeddingModel);
    }

    @GetMapping("/embeddingModelProvider/{embeddingModelProvider}")
    public List<Dataset> getDatasetsByEmbeddingModelProvider(@PathVariable(value = "embeddingModelProvider") String embeddingModelProvider) {
        return datasetService.getDatasetsByEmbeddingModelProvider(embeddingModelProvider);
    }

    @GetMapping("/collectionBindingId/{collectionBindingId}")
    public List<Dataset> getDatasetsByCollectionBindingId(@PathVariable(value = "collectionBindingId") UUID collectionBindingId) {
        return datasetService.getDatasetsByCollectionBindingId(collectionBindingId);
    }

    @GetMapping("/builtInFieldEnabled/{builtInFieldEnabled}")
    public List<Dataset> getDatasetsByBuiltInFieldEnabled(@PathVariable(value = "builtInFieldEnabled") boolean builtInFieldEnabled) {
        return datasetService.getDatasetsByBuiltInFieldEnabled(builtInFieldEnabled);
    }

    @PostMapping
    public Dataset createDataset(@RequestBody Dataset dataset) {
        return datasetService.createDataset(dataset);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dataset> updateDataset(@PathVariable(value = "id") UUID id, @RequestBody Dataset datasetDetails) {
        Dataset updatedDataset = datasetService.updateDataset(id, datasetDetails);
        return ResponseEntity.ok(updatedDataset);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDataset(@PathVariable(value = "id") UUID id) {
        datasetService.deleteDataset(id);
        return ResponseEntity.noContent().build();
    }
}
