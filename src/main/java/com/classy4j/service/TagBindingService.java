
package com.classy4j.service;

import com.classy4j.model.TagBinding;
import com.classy4j.repository.TagBindingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TagBindingService {

    @Autowired
    private TagBindingRepository repository;

    public List<TagBinding> findAll() {
        return repository.findAll();
    }

    public Optional<TagBinding> findById(UUID id) {
        return repository.findById(id);
    }

    public List<TagBinding> findByTenantId(UUID tenantId) {
        return repository.findByTenantId(tenantId);
    }

    public List<TagBinding> findByTagId(UUID tagId) {
        return repository.findByTagId(tagId);
    }

    public List<TagBinding> findByTargetId(UUID targetId) {
        return repository.findByTargetId(targetId);
    }

    public List<TagBinding> findByCreatedBy(UUID createdBy) {
        return repository.findByCreatedBy(createdBy);
    }

    public TagBinding save(TagBinding tagBinding) {
        return repository.save(tagBinding);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}