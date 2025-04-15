package com.classy4j.repository;

import com.classy4j.model.DatasetKeywordTable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DatasetKeywordTableRepository extends JpaRepository<DatasetKeywordTable, UUID> {
    List<DatasetKeywordTable> findByDatasetId(UUID datasetId);
    // 可以在这里添加自定义查询方法
}
