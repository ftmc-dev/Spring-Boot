package com.example.banckaccount.repository;

import com.example.banckaccount.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    boolean existsByAccountNumber(String accountNumber);
}
