package com.classy4j.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classy4j.model.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, String> {
    Provider findByProviderName(String providerName);

    List<Provider> findByTenantId(String tenantId);
    
    Optional<Provider> findByTenantIdAndProviderName(String tenantId, String providerName);
    
    List<Provider> findByTenantIdAndProviderType(String tenantId, String providerType);
    
    boolean existsByTenantIdAndProviderNameAndProviderType(String tenantId, String providerName, String providerType);
    
    void deleteByTenantIdAndProviderName(String tenantId, String providerName);
}