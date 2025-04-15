
package com.classy4j.service;

import com.classy4j.model.DatasetMetadata;
import com.classy4j.repository.DatasetMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DatasetMetadataService {

    @Autowired
    private DatasetMetadataRepository repository;

    public List<DatasetMetadata> findAll() {
        return repository.findAll();
    }

    public Optional<DatasetMetadata> findById(UUID id) {
        return repository.findById(id);
    }

    public List<DatasetMetadata> findByDatasetId(UUID datasetId) {
        return repository.findByDatasetId(datasetId);
    }

    public DatasetMetadata save(DatasetMetadata metadata) {
        return repository.save(metadata);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}