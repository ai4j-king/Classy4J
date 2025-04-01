package com.classy4j.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classy4j.model.Tenant;
import com.classy4j.repository.TenantRepository;

@Service
public class TenantService {
    @Autowired
    private TenantRepository tenantRepository;

    public List<Tenant> findAll() {
        return tenantRepository.findAll();
    }

    public Optional<Tenant> findById(String id) {
        return tenantRepository.findById(id);
    }

    public Tenant save(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    public void deleteById(String id) {
        tenantRepository.deleteById(id);
    }
}