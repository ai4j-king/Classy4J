package com.ai.classy4j.apps.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ai.classy4j.apps.application.entity.AppEntity;

@Repository
public interface AppRepository extends JpaRepository<AppEntity, String> {
    boolean existsByName(String name);
    
    @Query("SELECT a FROM AppEntity a WHERE (:keyword IS NULL OR (a.name LIKE %:keyword% OR a.description LIKE %:keyword%)) " +
           "AND (:type IS NULL OR a.type = :type) " +
           "AND (:status IS NULL OR a.status = :status)")
    List<AppEntity> findByFilters(
        @Param("keyword") String keyword,
        @Param("type") String type,
        @Param("status") String status
    );
}