package com.classy4j.repository;

import com.classy4j.model.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DatasetRepository extends JpaRepository<Dataset, UUID> {
    List<Dataset> findByTenantId(UUID tenantId);
    List<Dataset> findByName(String name);
    List<Dataset> findByProvider(String provider);
    List<Dataset> findByPermission(String permission);
    List<Dataset> findByDataSourceType(String dataSourceType);
    List<Dataset> findByIndexingTechnique(String indexingTechnique);
    List<Dataset> findByEmbeddingModel(String embeddingModel);
    List<Dataset> findByEmbeddingModelProvider(String embeddingModelProvider);
    List<Dataset> findByCollectionBindingId(UUID collectionBindingId);
    List<Dataset> findByBuiltInFieldEnabled(boolean builtInFieldEnabled);
}

