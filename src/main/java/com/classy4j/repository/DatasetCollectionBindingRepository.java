package com.classy4j.repository;

import com.classy4j.model.DatasetCollectionBinding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DatasetCollectionBindingRepository extends JpaRepository<DatasetCollectionBinding, UUID> {
}