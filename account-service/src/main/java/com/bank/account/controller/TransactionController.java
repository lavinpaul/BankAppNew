package com.bank.account.controller;

import com.bank.account.entity.Transaction;
import com.bank.account.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/account/{accountId}")
    public List<Transaction> getTransactions(
            @PathVariable Long accountId) {

        return transactionService.getTransactions(accountId);
    }
}