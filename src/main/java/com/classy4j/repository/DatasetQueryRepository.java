
package com.classy4j.repository;

import com.classy4j.model.DatasetQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DatasetQueryRepository extends JpaRepository<DatasetQuery, UUID> {
    List<DatasetQuery> findByDatasetId(UUID datasetId);
    List<DatasetQuery> findBySource(String source);
    List<DatasetQuery> findBySourceAppId(UUID sourceAppId);
    List<DatasetQuery> findByCreatedBy(UUID createdBy);
    // 可以在这里添加自定义查询方法
}