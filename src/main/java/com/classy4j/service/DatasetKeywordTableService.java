package com.classy4j.service;


import com.classy4j.model.DatasetKeywordTable;
import com.classy4j.repository.DatasetKeywordTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DatasetKeywordTableService {

    @Autowired
    private DatasetKeywordTableRepository repository;

    public List<DatasetKeywordTable> findAll() {
        return repository.findAll();
    }

    public Optional<DatasetKeywordTable> findById(UUID id) {
        return repository.findById(id);
    }

    public List<DatasetKeywordTable> findByDatasetId(UUID datasetId) {
        return repository.findByDatasetId(datasetId);
    }

    public DatasetKeywordTable save(DatasetKeywordTable keywordTable) {
        return repository.save(keywordTable);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
