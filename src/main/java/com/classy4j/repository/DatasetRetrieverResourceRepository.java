
package com.classy4j.repository;

import com.classy4j.model.DatasetRetrieverResource;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DatasetRetrieverResourceRepository extends JpaRepository<DatasetRetrieverResource, UUID> {
    List<DatasetRetrieverResource> findByDatasetId(UUID datasetId);
    List<DatasetRetrieverResource> findByDocumentId(UUID documentId);
    List<DatasetRetrieverResource> findByMessageId(UUID messageId);
    List<DatasetRetrieverResource> findByCreatedBy(UUID createdBy);
    // 可以在这里添加自定义查询方法
}