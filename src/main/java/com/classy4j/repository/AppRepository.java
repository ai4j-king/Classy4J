package com.classy4j.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classy4j.model.App;

@Repository
public interface AppRepository extends JpaRepository<App, UUID> {
    Page<App> findByCreatedByAndTenantId(UUID userId, UUID tenantId, Pageable pageable);
}