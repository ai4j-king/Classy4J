package com.classy4j.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classy4j.model.App;

@Repository
public interface AppRepository extends JpaRepository<App, String> {
    Page<App> findByCreatedByAndTenantId(String userId, String tenantId, Pageable pageable);
    
    List<App> findByTenantIdAndMode(String tenantId, String mode);
    
    App findByTenantIdAndName(String tenantId, String name);
    
    boolean existsByTenantIdAndName(String tenantId, String name);
    
    List<App> findByCreatedBy(String createdBy);
    
    List<App> findByTenantIdAndIsPublic(String tenantId, boolean isPublic);
    
    List<App> findByTenantIdAndIsUniversal(String tenantId, boolean isUniversal);
}