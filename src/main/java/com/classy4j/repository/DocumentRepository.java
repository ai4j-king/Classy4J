
package com.classy4j.repository;

import com.classy4j.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {
    List<Document> findByTenantId(UUID tenantId);
    List<Document> findByDatasetId(UUID datasetId);
    List<Document> findByBatch(String batch);
    List<Document> findByIsPaused(boolean isPaused);
    List<Document> findByEnabled(boolean enabled);
    List<Document> findByArchived(boolean archived);
    // 可以在这里添加自定义查询方法
    long countByDatasetId(UUID datasetId);

    @Query("SELECT SUM(d.wordCount) FROM Document d WHERE d.datasetId = :datasetId")
    Integer sumWordCountByDatasetId(@Param("datasetId") UUID datasetId);

    Optional<Document> findFirstByDatasetId(UUID datasetId);
}