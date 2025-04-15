
package com.classy4j.service;

import com.classy4j.model.DatasetRetrieverResource;
import com.classy4j.repository.DatasetRetrieverResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DatasetRetrieverResourceService {

    @Autowired
    private DatasetRetrieverResourceRepository repository;

    public List<DatasetRetrieverResource> findAll() {
        return repository.findAll();
    }

    public Optional<DatasetRetrieverResource> findById(UUID id) {
        return repository.findById(id);
    }

    public List<DatasetRetrieverResource> findByDatasetId(UUID datasetId) {
        return repository.findByDatasetId(datasetId);
    }

    public List<DatasetRetrieverResource> findByDocumentId(UUID documentId) {
        return repository.findByDocumentId(documentId);
    }

    public List<DatasetRetrieverResource> findByMessageId(UUID messageId) {
        return repository.findByMessageId(messageId);
    }

    public List<DatasetRetrieverResource> findByCreatedBy(UUID createdBy) {
        return repository.findByCreatedBy(createdBy);
    }

    public DatasetRetrieverResource save(DatasetRetrieverResource resource) {
        return repository.save(resource);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}