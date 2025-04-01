package com.classy4j.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classy4j.model.AccountIntegrate;

@Repository
public interface AccountIntegrateRepository extends JpaRepository<AccountIntegrate, String> {
    List<AccountIntegrate> findByAccountId(String accountId);
    AccountIntegrate findByAccountIdAndProvider(String accountId, String provider);
    AccountIntegrate findByProviderAndOpenId(String provider, String openId);
}