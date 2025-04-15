
package com.classy4j.service;

import com.classy4j.model.DatasetMetadataBinding;
import com.classy4j.repository.DatasetMetadataBindingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DatasetMetadataBindingService {

    @Autowired
    private DatasetMetadataBindingRepository repository;

    public List<DatasetMetadataBinding> findAll() {
        return repository.findAll();
    }

    public Optional<DatasetMetadataBinding> findById(UUID id) {
        return repository.findById(id);
    }

    public List<DatasetMetadataBinding> findByDatasetId(UUID datasetId) {
        return repository.findByDatasetId(datasetId);
    }

    public List<DatasetMetadataBinding> findByMetadataId(UUID metadataId) {
        return repository.findByMetadataId(metadataId);
    }

    public List<DatasetMetadataBinding> findByDocumentId(UUID documentId) {
        return repository.findByDocumentId(documentId);
    }

    public DatasetMetadataBinding save(DatasetMetadataBinding binding) {
        return repository.save(binding);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}