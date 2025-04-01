package com.classy4j.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classy4j.model.AppModelConfig;

@Repository
public interface AppModelConfigRepository extends JpaRepository<AppModelConfig, String> {
    boolean existsByAppId(String appId);
}