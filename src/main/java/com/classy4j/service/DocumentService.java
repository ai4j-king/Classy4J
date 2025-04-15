
package com.classy4j.service;


import com.classy4j.model.Document;
import com.classy4j.repository.DocumentRepository;
import com.classy4j.util.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository repository;

    public List<Document> findAll() {
        return repository.findAll();
    }

    public Optional<Document> findById(UUID id) {
        return repository.findById(id);
    }

    public List<Document> findByTenantId(UUID tenantId) {
        return repository.findByTenantId(tenantId);
    }

    public List<Document> findByDatasetId(UUID datasetId) {
        return repository.findByDatasetId(datasetId);
    }

    public List<Document> findByBatch(String batch) {
        return repository.findByBatch(batch);
    }

    public List<Document> findByIsPaused(boolean isPaused) {
        return repository.findByIsPaused(isPaused);
    }

    public List<Document> findByEnabled(boolean enabled) {
        return repository.findByEnabled(enabled);
    }

    public List<Document> findByArchived(boolean archived) {
        return repository.findByArchived(archived);
    }

    public Document save(Document document) {
        return repository.save(document);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public long countByDatasetId(UUID datasetId) {
        return repository.countByDatasetId(datasetId);
    }

    public int sumWordCountByDatasetId(UUID datasetId) {
        return NumberUtils.orZero(repository.sumWordCountByDatasetId(datasetId));
    }

    public Optional<Document> findFirstByDatasetId(UUID datasetId) {
        return repository.findFirstByDatasetId(datasetId);
    }
}