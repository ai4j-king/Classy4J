
package com.classy4j.repository;

import com.classy4j.model.DatasetMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DatasetMetadataRepository extends JpaRepository<DatasetMetadata, UUID> {
    List<DatasetMetadata> findByDatasetId(UUID datasetId);
    // 可以在这里添加自定义查询方法
}