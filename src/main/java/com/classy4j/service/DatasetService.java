
package com.classy4j.service;

import com.classy4j.model.Dataset;
import com.classy4j.model.DatasetMetadata;
import com.classy4j.model.Document;
import com.classy4j.model.Tag;
import com.classy4j.repository.DatasetRepository;
import com.classy4j.util.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DatasetService {

    @Autowired
    private DatasetRepository datasetRepository;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private TagBindingService tagBindingService;

    @Autowired
    private TagService tagService;

    @Autowired
    private DatasetMetadataService datasetMetadataService;

    public Page<Dataset> findAll(Pageable pageable) {
        return datasetRepository.findAll(pageable);
    }

    public Optional<Dataset> getDatasetById(UUID id) {
        return datasetRepository.findById(id);
    }

    public List<Dataset> getDatasetsByTenantId(UUID tenantId) {
        return datasetRepository.findByTenantId(tenantId);
    }

    public List<Dataset> getDatasetsByName(String name) {
        return datasetRepository.findByName(name);
    }

    public List<Dataset> getDatasetsByProvider(String provider) {
        return datasetRepository.findByProvider(provider);
    }

    public List<Dataset> getDatasetsByPermission(String permission) {
        return datasetRepository.findByPermission(permission);
    }

    public List<Dataset> getDatasetsByDataSourceType(String dataSourceType) {
        return datasetRepository.findByDataSourceType(dataSourceType);
    }

    public List<Dataset> getDatasetsByIndexingTechnique(String indexingTechnique) {
        return datasetRepository.findByIndexingTechnique(indexingTechnique);
    }

    public List<Dataset> getDatasetsByEmbeddingModel(String embeddingModel) {
        return datasetRepository.findByEmbeddingModel(embeddingModel);
    }

    public List<Dataset> getDatasetsByEmbeddingModelProvider(String embeddingModelProvider) {
        return datasetRepository.findByEmbeddingModelProvider(embeddingModelProvider);
    }

    public List<Dataset> getDatasetsByCollectionBindingId(UUID collectionBindingId) {
        return datasetRepository.findByCollectionBindingId(collectionBindingId);
    }

    public List<Dataset> getDatasetsByBuiltInFieldEnabled(boolean builtInFieldEnabled) {
        return datasetRepository.findByBuiltInFieldEnabled(builtInFieldEnabled);
    }

    public Dataset createDataset(Dataset dataset) {
        return datasetRepository.save(dataset);
    }

    public Dataset updateDataset(UUID id, Dataset datasetDetails) {
        Dataset dataset = datasetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dataset not found for this id :: " + id));
        dataset.setName(datasetDetails.getName());
        dataset.setDescription(datasetDetails.getDescription());
        dataset.setProvider(datasetDetails.getProvider());
        dataset.setPermission(datasetDetails.getPermission());
        dataset.setDataSourceType(datasetDetails.getDataSourceType());
        dataset.setIndexingTechnique(datasetDetails.getIndexingTechnique());
        dataset.setIndexStruct(datasetDetails.getIndexStruct());
        dataset.setCreatedBy(datasetDetails.getCreatedBy());
        dataset.setCreatedAt(datasetDetails.getCreatedAt());
        dataset.setUpdatedBy(datasetDetails.getUpdatedBy());
        dataset.setUpdatedAt(datasetDetails.getUpdatedAt());
        dataset.setEmbeddingModel(datasetDetails.getEmbeddingModel());
        dataset.setEmbeddingModelProvider(datasetDetails.getEmbeddingModelProvider());
        dataset.setCollectionBindingId(datasetDetails.getCollectionBindingId());
        dataset.setRetrievalModel(datasetDetails.getRetrievalModel());
        dataset.setBuiltInFieldEnabled(datasetDetails.isBuiltInFieldEnabled());
        final Dataset updatedDataset = datasetRepository.save(dataset);
        return updatedDataset;
    }

    public void deleteDataset(UUID id) {
        Dataset dataset = datasetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dataset not found for this id :: " + id));
        datasetRepository.delete(dataset);
    }

    public List<Map<String, Object>> getExtendedDatasetInfo(List<Dataset> datasets, boolean includeAll) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (Dataset dataset : datasets) {
            Map<String, Object> datasetInfo = new HashMap<>();
            datasetInfo.put("id", dataset.getId());
            datasetInfo.put("tenantId", dataset.getTenantId());
            datasetInfo.put("name", dataset.getName());
            datasetInfo.put("description", dataset.getDescription());
            datasetInfo.put("provider", dataset.getProvider());
            datasetInfo.put("permission", dataset.getPermission());
            datasetInfo.put("dataSourceType", dataset.getDataSourceType());
            datasetInfo.put("indexingTechnique", dataset.getIndexingTechnique());
            datasetInfo.put("indexStruct", dataset.getIndexStruct());
            datasetInfo.put("createdBy", dataset.getCreatedBy());
            datasetInfo.put("createdAt", dataset.getCreatedAt());
            datasetInfo.put("updatedBy", dataset.getUpdatedBy());
            datasetInfo.put("updatedAt", dataset.getUpdatedAt());
            datasetInfo.put("embeddingModel", dataset.getEmbeddingModel());
            datasetInfo.put("embeddingModelProvider", dataset.getEmbeddingModelProvider());
            datasetInfo.put("collectionBindingId", dataset.getCollectionBindingId());
            datasetInfo.put("retrievalModel", dataset.getRetrievalModel());
            datasetInfo.put("builtInFieldEnabled", dataset.isBuiltInFieldEnabled());

            if (includeAll) {
                // 2.1 documentCount: 查询“Document”表中对应dataset_id值的数量
                long documentCount = documentService.countByDatasetId(dataset.getId());
                datasetInfo.put("documentCount", documentCount);

                // 2.2 wordCount: 查询“Document”表中对应dataset_id值的word_count的之和
                int wordCount = documentService.sumWordCountByDatasetId(dataset.getId());
                datasetInfo.put("wordCount", wordCount);

                // 2.3 tags: 关联tag表tab_binding表
                List<Tag> tags = tagService.findByDatasetId(dataset.getId(), dataset.getTenantId());
                datasetInfo.put("tags", tags.stream().map(Tag::getName).collect(Collectors.toList()));

                // 2.4 docForm: 关联Document表
                Optional<Document> firstDocument = documentService.findFirstByDatasetId(dataset.getId());
                if (firstDocument.isPresent()) {
                    datasetInfo.put("docForm", firstDocument.get().getDocForm());
                } else {
                    datasetInfo.put("docForm", null);
                }

                // 2.5 docMetadata: 关联DatasetMetadata
                List<DatasetMetadata> metadataList = datasetMetadataService.findByDatasetId(dataset.getId());
                datasetInfo.put("docMetadata", metadataList);
            }

            result.add(datasetInfo);
        }

        return result;
    }
}