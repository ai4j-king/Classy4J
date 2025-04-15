package com.classy4j.service;


import com.classy4j.model.DatasetCollectionBinding;
import com.classy4j.repository.DatasetCollectionBindingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DatasetCollectionBindingService {

    @Autowired
    private DatasetCollectionBindingRepository repository;

    public List<DatasetCollectionBinding> findAll() {
        return repository.findAll();
    }

    public Optional<DatasetCollectionBinding> findById(UUID id) {
        return repository.findById(id);
    }

    public DatasetCollectionBinding save(DatasetCollectionBinding binding) {
        return repository.save(binding);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
