
package com.classy4j.service;

import com.classy4j.model.DatasetQuery;
import com.classy4j.repository.DatasetQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DatasetQueryService {

    @Autowired
    private DatasetQueryRepository repository;

    public List<DatasetQuery> findAll() {
        return repository.findAll();
    }

    public Optional<DatasetQuery> findById(UUID id) {
        return repository.findById(id);
    }

    public List<DatasetQuery> findByDatasetId(UUID datasetId) {
        return repository.findByDatasetId(datasetId);
    }

    public List<DatasetQuery> findBySource(String source) {
        return repository.findBySource(source);
    }

    public List<DatasetQuery> findBySourceAppId(UUID sourceAppId) {
        return repository.findBySourceAppId(sourceAppId);
    }

    public List<DatasetQuery> findByCreatedBy(UUID createdBy) {
        return repository.findByCreatedBy(createdBy);
    }

    public DatasetQuery save(DatasetQuery query) {
        return repository.save(query);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}