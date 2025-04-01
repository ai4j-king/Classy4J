package com.classy4j.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classy4j.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByUsername(String username);
    Account findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}