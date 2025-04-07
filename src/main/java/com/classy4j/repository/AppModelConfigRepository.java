package com.classy4j.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classy4j.model.AppModelConfig;

@Repository
public interface AppModelConfigRepository extends JpaRepository<AppModelConfig, UUID> {
    boolean existsByAppId(UUID appId);
}