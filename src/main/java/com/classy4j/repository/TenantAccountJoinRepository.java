package com.classy4j.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classy4j.model.TenantAccountJoin;

@Repository
public interface TenantAccountJoinRepository extends JpaRepository<TenantAccountJoin, String> {
    List<TenantAccountJoin> findByAccountId(String accountId);
    List<TenantAccountJoin> findByTenantId(String tenantId);
    TenantAccountJoin findByAccountIdAndTenantId(String accountId, String tenantId);
}