package com.classy4j.service;

import com.classy4j.model.Tag;
import com.classy4j.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Optional<Tag> getTagById(UUID id) {
        return tagRepository.findById(id);
    }

    public List<Tag> getTagsByTenantId(UUID tenantId) {
        return tagRepository.findByTenantId(tenantId);
    }

    public List<Tag> getTagsByType(String type) {
        return tagRepository.findByType(type);
    }

    public List<Tag> getTagsByName(String name) {
        return tagRepository.findByName(name);
    }

    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag updateTag(UUID id, Tag tagDetails) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found for this id :: " + id));
        tag.setType(tagDetails.getType());
        tag.setName(tagDetails.getName());
        tag.setCreatedBy(tagDetails.getCreatedBy());
        final Tag updatedTag = tagRepository.save(tag);
        return updatedTag;
    }

    public void deleteTag(UUID id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found for this id :: " + id));
        tagRepository.delete(tag);
    }

    public List<Tag> findByDatasetId(UUID datasetId, UUID tenantId) {
        return tagRepository.findByDatasetId(datasetId, tenantId);
    }
}

