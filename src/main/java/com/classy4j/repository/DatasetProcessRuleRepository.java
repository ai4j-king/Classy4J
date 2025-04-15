
package com.classy4j.repository;

import com.classy4j.model.DatasetProcessRule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DatasetProcessRuleRepository extends JpaRepository<DatasetProcessRule, UUID> {
    List<DatasetProcessRule> findByDatasetId(UUID datasetId);
    // 可以在这里添加自定义查询方法
}