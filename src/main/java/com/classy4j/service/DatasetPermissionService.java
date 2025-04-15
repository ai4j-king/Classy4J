
package com.classy4j.service;

import com.classy4j.model.DatasetPermission;
import com.classy4j.repository.DatasetPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DatasetPermissionService {

    @Autowired
    private DatasetPermissionRepository repository;

    public List<DatasetPermission> findAll() {
        return repository.findAll();
    }

    public Optional<DatasetPermission> findById(UUID id) {
        return repository.findById(id);
    }

    public List<DatasetPermission> findByDatasetId(UUID datasetId) {
        return repository.findByDatasetId(datasetId);
    }

    public List<DatasetPermission> findByAccountId(UUID accountId) {
        return repository.findByAccountId(accountId);
    }

    public List<DatasetPermission> findByTenantId(UUID tenantId) {
        return repository.findByTenantId(tenantId);
    }

    public DatasetPermission save(DatasetPermission permission) {
        return repository.save(permission);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}