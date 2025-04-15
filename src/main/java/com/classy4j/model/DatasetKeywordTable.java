package com.classy4j.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "dataset_keyword_tables")
@Data
public class DatasetKeywordTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "dataset_id", nullable = false)
    private UUID datasetId;

    @Column(name = "keyword_table", nullable = false, columnDefinition = "TEXT")
    private String keywordTable;

    @Column(name = "data_source_type", nullable = false, length = 255)
    private String dataSourceType = "database";

}
