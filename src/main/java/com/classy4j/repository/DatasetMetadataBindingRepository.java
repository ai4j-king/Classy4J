
package com.classy4j.repository;

import com.classy4j.model.DatasetMetadataBinding;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DatasetMetadataBindingRepository extends JpaRepository<DatasetMetadataBinding, UUID> {
    List<DatasetMetadataBinding> findByDatasetId(UUID datasetId);
    List<DatasetMetadataBinding> findByMetadataId(UUID metadataId);
    List<DatasetMetadataBinding> findByDocumentId(UUID documentId);
    // 可以在这里添加自定义查询方法
}