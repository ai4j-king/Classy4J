
package com.classy4j.service;

import com.classy4j.model.DocumentSegment;
import com.classy4j.repository.DocumentSegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentSegmentService {

    @Autowired
    private DocumentSegmentRepository repository;

    public List<DocumentSegment> findAll() {
        return repository.findAll();
    }

    public Optional<DocumentSegment> findById(UUID id) {
        return repository.findById(id);
    }

    public List<DocumentSegment> findByTenantId(UUID tenantId) {
        return repository.findByTenantId(tenantId);
    }

    public List<DocumentSegment> findByDatasetId(UUID datasetId) {
        return repository.findByDatasetId(datasetId);
    }

    public List<DocumentSegment> findByDocumentId(UUID documentId) {
        return repository.findByDocumentId(documentId);
    }

    public List<DocumentSegment> findByEnabled(boolean enabled) {
        return repository.findByEnabled(enabled);
    }

    public List<DocumentSegment> findByStatus(String status) {
        return repository.findByStatus(status);
    }

    public DocumentSegment save(DocumentSegment segment) {
        return repository.save(segment);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}