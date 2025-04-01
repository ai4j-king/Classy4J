package com.classy4j.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classy4j.model.Account;
import com.classy4j.repository.AccountRepository;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
    
    public Optional<Account> findById(String id) {
        return accountRepository.findById(id);
    }
    
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
    
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
    
    public Account save(Account account) {
        return accountRepository.save(account);
    }
    
    public void deleteById(String id) {
        accountRepository.deleteById(id);
    }
    
    public boolean existsByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }
    
    public boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }
}