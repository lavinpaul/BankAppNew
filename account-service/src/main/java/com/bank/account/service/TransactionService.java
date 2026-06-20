package com.bank.account.service;

import com.bank.account.entity.Transaction;
import com.bank.account.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public List<Transaction> getTransactions(
            Long accountId) {

        return transactionRepository.findByAccountId(accountId);
    }
}