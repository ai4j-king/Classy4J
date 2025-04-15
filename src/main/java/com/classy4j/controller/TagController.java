package com.classy4j.controller;

import com.classy4j.model.Tag;
import com.classy4j.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable(value = "id") UUID id) {
        Tag tag = tagService.getTagById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found for this id :: " + id));
        return ResponseEntity.ok().body(tag);
    }

    @GetMapping("/tenant/{tenantId}")
    public List<Tag> getTagsByTenantId(@PathVariable(value = "tenantId") UUID tenantId) {
        return tagService.getTagsByTenantId(tenantId);
    }

    @GetMapping("/type/{type}")
    public List<Tag> getTagsByType(@PathVariable(value = "type") String type) {
        return tagService.getTagsByType(type);
    }

    @GetMapping("/name/{name}")
    public List<Tag> getTagsByName(@PathVariable(value = "name") String name) {
        return tagService.getTagsByName(name);
    }

    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable(value = "id") UUID id, @RequestBody Tag tagDetails) {
        Tag updatedTag = tagService.updateTag(id, tagDetails);
        return ResponseEntity.ok(updatedTag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable(value = "id") UUID id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}

