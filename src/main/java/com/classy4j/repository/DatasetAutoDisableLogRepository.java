
package com.classy4j.repository;

import com.classy4j.model.DatasetAutoDisableLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DatasetAutoDisableLogRepository extends JpaRepository<DatasetAutoDisableLog, UUID> {
    List<DatasetAutoDisableLog> findByTenantId(UUID tenantId);
    List<DatasetAutoDisableLog> findByDatasetId(UUID datasetId);
    List<DatasetAutoDisableLog> findByDocumentId(UUID documentId);
    List<DatasetAutoDisableLog> findByNotified(boolean notified);
    // 可以在这里添加自定义查询方法
}