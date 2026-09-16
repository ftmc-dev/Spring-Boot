package com.example.banckaccount.controller;

import com.example.banckaccount.model.Bank;
import com.example.banckaccount.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping
    public Bank createBank(@RequestBody Bank bank) {
        return bankService.saveBank(bank);
    }

    @GetMapping
    public List<Bank> getAllBanks() {
        return bankService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Bank getBankById(@PathVariable Long id) {
        return bankService.getBankById(id);
    }

    @PutMapping("/{id}")
    public Bank updateBank(@PathVariable Long id, @RequestBody Bank bank) {
        return bankService.updateBank(id, bank);
    }

    @DeleteMapping("/{id}")
    public void deleteBankById(@PathVariable Long id) {
        bankService.deleteBankById(id);
    }

    @PostMapping("/{id}/deposit")
    public Bank createDeposit(@PathVariable Long id, @RequestParam double amountToDeposit) {
        return bankService.createDeposit(id, amountToDeposit);
    }

    @PostMapping("/{id}/withdraw")
    public Bank createWithdraw(@PathVariable Long id, @RequestParam double amountToWithdraw) {
        return bankService.createWithdraw(id, amountToWithdraw);
    }
}