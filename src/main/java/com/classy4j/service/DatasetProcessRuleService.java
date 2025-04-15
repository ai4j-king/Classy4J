
package com.classy4j.service;

import com.classy4j.model.DatasetProcessRule;
import com.classy4j.repository.DatasetProcessRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DatasetProcessRuleService {

    @Autowired
    private DatasetProcessRuleRepository repository;

    public List<DatasetProcessRule> findAll() {
        return repository.findAll();
    }

    public Optional<DatasetProcessRule> findById(UUID id) {
        return repository.findById(id);
    }

    public List<DatasetProcessRule> findByDatasetId(UUID datasetId) {
        return repository.findByDatasetId(datasetId);
    }

    public DatasetProcessRule save(DatasetProcessRule rule) {
        return repository.save(rule);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}