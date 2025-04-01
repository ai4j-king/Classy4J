package com.classy4j.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classy4j.model.AccountIntegrate;
import com.classy4j.repository.AccountIntegrateRepository;

@Service
public class AccountIntegrateService {
    @Autowired
    private AccountIntegrateRepository accountIntegrateRepository;

    public List<AccountIntegrate> findByAccountId(String accountId) {
        return accountIntegrateRepository.findByAccountId(accountId);
    }

    public AccountIntegrate findByAccountIdAndProvider(String accountId, String provider) {
        return accountIntegrateRepository.findByAccountIdAndProvider(accountId, provider);
    }

    public AccountIntegrate findByProviderAndOpenId(String provider, String openId) {
        return accountIntegrateRepository.findByProviderAndOpenId(provider, openId);
    }

    public AccountIntegrate save(AccountIntegrate accountIntegrate) {
        return accountIntegrateRepository.save(accountIntegrate);
    }

    public void deleteById(String id) {
        accountIntegrateRepository.deleteById(id);
    }
}