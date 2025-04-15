
package com.classy4j.repository;

import com.classy4j.model.TagBinding;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface TagBindingRepository extends JpaRepository<TagBinding, UUID> {
    List<TagBinding> findByTenantId(UUID tenantId);
    List<TagBinding> findByTagId(UUID tagId);
    List<TagBinding> findByTargetId(UUID targetId);
    List<TagBinding> findByCreatedBy(UUID createdBy);
    // 可以在这里添加自定义查询方法
}