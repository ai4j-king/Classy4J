
package com.classy4j.repository;

import com.classy4j.model.DatasetPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DatasetPermissionRepository extends JpaRepository<DatasetPermission, UUID> {
    List<DatasetPermission> findByDatasetId(UUID datasetId);
    List<DatasetPermission> findByAccountId(UUID accountId);
    List<DatasetPermission> findByTenantId(UUID tenantId);
    // 可以在这里添加自定义查询方法
}