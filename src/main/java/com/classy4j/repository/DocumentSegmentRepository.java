
package com.classy4j.repository;

import com.classy4j.model.DocumentSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DocumentSegmentRepository extends JpaRepository<DocumentSegment, UUID> {
    List<DocumentSegment> findByTenantId(UUID tenantId);
    List<DocumentSegment> findByDatasetId(UUID datasetId);
    List<DocumentSegment> findByDocumentId(UUID documentId);
    List<DocumentSegment> findByEnabled(boolean enabled);
    List<DocumentSegment> findByStatus(String status);
    // 可以在这里添加自定义查询方法
}