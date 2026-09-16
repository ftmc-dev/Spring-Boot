package com.example.banckaccount.service;

import com.example.banckaccount.model.Bank;
import com.example.banckaccount.repository.BankRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {
    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public Bank saveBank(Bank bank) {
        if(bankRepository.existsByAccountNumber(bank.getAccountNumber())){
            throw new RuntimeException("Bank already exists");
        }
        bank.setBalance(0.0);
        return bankRepository.save(bank);
    }

    public List<Bank> getAllAccounts() {
        return bankRepository.findAll();
    }

    public Bank getBankById(Long id) {
        Optional<Bank> bank = bankRepository.findById(id);
        if (bank.isEmpty()) {
            throw new RuntimeException("Bank not found");
        }
        return bank.get();
    }

    public Bank updateBank(Long id, Bank bank) {
        Optional<Bank> bankOptional = bankRepository.findById(id);
        if (bankOptional.isEmpty()) {
            throw new RuntimeException("Bank not found");
        }

        Bank bankToUpdate = bankOptional.get();
        bankToUpdate.setAccountNumber(bank.getAccountNumber());
        bankToUpdate.setOwnerName(bank.getOwnerName());
        return bankRepository.save(bankToUpdate);
    }

    public void deleteBankById(Long id) {
        if (bankRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Bank not found");
        }
            bankRepository.deleteById(id);
    }

    public Bank createDeposit(Long id, double amountToDeposit) {
        if(amountToDeposit <= 0 ){
            throw new RuntimeException("Deposit amount can't be negative");
        }

        Bank bank = bankRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank not found"));
        bank.setBalance(bank.getBalance() + amountToDeposit);
        return bankRepository.save(bank);

    }

    public Bank createWithdraw(Long id, double amountToWithdraw) {
        if(amountToWithdraw <= 0){
            throw new RuntimeException("Withdrawal can't be negative");
        }

        Bank bank = bankRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank not found"));

        if(bank.getBalance() < amountToWithdraw){
            throw new RuntimeException("You don't have sufficient funds");
        }

        bank.setBalance(bank.getBalance() - amountToWithdraw);
        return bankRepository.save(bank);
    }
}
