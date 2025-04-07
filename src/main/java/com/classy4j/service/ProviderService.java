package com.classy4j.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.classy4j.model.Provider;
import com.classy4j.repository.ProviderRepository;

@Service
public class ProviderService {
    private final ProviderRepository providerRepository;

    @Autowired
    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public Provider findByProviderName(String providerName) {
        return providerRepository.findByProviderName(providerName);
    }

    public List<Provider> getProvidersByTenantId(String tenantId) {
        return providerRepository.findByTenantId(tenantId);
    }

    public Optional<Provider> getProviderByTenantIdAndName(String tenantId, String providerName) {
        return providerRepository.findByTenantIdAndProviderName(tenantId, providerName);
    }

    public List<Provider> getProvidersByTenantIdAndType(String tenantId, String providerType) {
        return providerRepository.findByTenantIdAndProviderType(tenantId, providerType);
    }

    @Transactional
    public Provider createProvider(Provider provider) {
        if (providerRepository.existsByTenantIdAndProviderNameAndProviderType(
                provider.getTenantId(), provider.getProviderName(), provider.getProviderType())) {
            throw new IllegalArgumentException("Provider with the same name and type already exists");
        }
        return providerRepository.save(provider);
    }

    @Transactional
    public Provider updateProvider(Provider provider) {
        Optional<Provider> existingProvider = providerRepository.findByTenantIdAndProviderName(
                provider.getTenantId(), provider.getProviderName());
        if (existingProvider.isEmpty()) {
            throw new IllegalArgumentException("Provider not found");
        }
        Provider updatedProvider = existingProvider.get();
        updatedProvider.setProviderType(provider.getProviderType());
        updatedProvider.setEncryptedConfig(provider.getEncryptedConfig());
        updatedProvider.setIsValid(provider.getIsValid());
        updatedProvider.setQuotaType(provider.getQuotaType());
        updatedProvider.setQuotaLimit(provider.getQuotaLimit());
        updatedProvider.setQuotaUsed(provider.getQuotaUsed());
        return providerRepository.save(updatedProvider);
    }

    @Transactional
    public void deleteProvider(String tenantId, String providerName) {
        providerRepository.deleteByTenantIdAndProviderName(tenantId, providerName);
    }

    @Transactional
    public void updateProviderUsage(String tenantId, String providerName, long usageIncrement) {
        Optional<Provider> providerOpt = providerRepository.findByTenantIdAndProviderName(tenantId, providerName);
        if (providerOpt.isPresent()) {
            Provider provider = providerOpt.get();
            provider.setQuotaUsed(provider.getQuotaUsed() + usageIncrement);
            provider.setLastUsed(LocalDateTime.now());
            providerRepository.save(provider);
        }
    }

    public boolean isProviderEnabled(String tenantId, String providerName) {
        Optional<Provider> providerOpt = providerRepository.findByTenantIdAndProviderName(tenantId, providerName);
        return providerOpt.map(Provider::isEnabled).orElse(false);
    }

    public boolean hasAvailableQuota(String tenantId, String providerName) {
        Optional<Provider> providerOpt = providerRepository.findByTenantIdAndProviderName(tenantId, providerName);
        if (providerOpt.isPresent()) {
            Provider provider = providerOpt.get();
            return provider.getQuotaLimit() == null || provider.getQuotaUsed() < provider.getQuotaLimit();
        }
        return false;
    }
}