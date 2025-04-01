package com.classy4j.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classy4j.model.TenantAccountJoin;
import com.classy4j.repository.TenantAccountJoinRepository;

@Service
public class TenantAccountJoinService {
    @Autowired
    private TenantAccountJoinRepository tenantAccountJoinRepository;

    public List<TenantAccountJoin> findByAccountId(String accountId) {
        return tenantAccountJoinRepository.findByAccountId(accountId);
    }

    public List<TenantAccountJoin> findByTenantId(String tenantId) {
        return tenantAccountJoinRepository.findByTenantId(tenantId);
    }

    public TenantAccountJoin findByAccountIdAndTenantId(String accountId, String tenantId) {
        return tenantAccountJoinRepository.findByAccountIdAndTenantId(accountId, tenantId);
    }

    public TenantAccountJoin save(TenantAccountJoin tenantAccountJoin) {
        return tenantAccountJoinRepository.save(tenantAccountJoin);
    }

    public void deleteById(String id) {
        tenantAccountJoinRepository.deleteById(id);
    }
}