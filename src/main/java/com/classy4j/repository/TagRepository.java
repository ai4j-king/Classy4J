package com.classy4j.repository;

import com.classy4j.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {
    List<Tag> findByTenantId(UUID tenantId);
    List<Tag> findByType(String type);
    List<Tag> findByName(String name);

    @Query("SELECT t FROM Tag t JOIN TagBinding tb ON t.id = tb.tagId WHERE tb.targetId = :datasetId AND tb.tenantId = :tenantId AND t.tenantId = :tenantId AND t.type = 'knowledge'")
    List<Tag> findByDatasetId(@Param("datasetId") UUID datasetId, @Param("tenantId") UUID targetId);

}
