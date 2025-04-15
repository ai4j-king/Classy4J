
package com.classy4j.service;

import com.classy4j.model.DatasetAutoDisableLog;
import com.classy4j.repository.DatasetAutoDisableLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DatasetAutoDisableLogService {

    @Autowired
    private DatasetAutoDisableLogRepository repository;

    public List<DatasetAutoDisableLog> findAll() {
        return repository.findAll();
    }

    public Optional<DatasetAutoDisableLog> findById(UUID id) {
        return repository.findById(id);
    }

    public List<DatasetAutoDisableLog> findByTenantId(UUID tenantId) {
        return repository.findByTenantId(tenantId);
    }

    public List<DatasetAutoDisableLog> findByDatasetId(UUID datasetId) {
        return repository.findByDatasetId(datasetId);
    }

    public List<DatasetAutoDisableLog> findByDocumentId(UUID documentId) {
        return repository.findByDocumentId(documentId);
    }

    public List<DatasetAutoDisableLog> findByNotified(boolean notified) {
        return repository.findByNotified(notified);
    }

    public DatasetAutoDisableLog save(DatasetAutoDisableLog log) {
        return repository.save(log);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}