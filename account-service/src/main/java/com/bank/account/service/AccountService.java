package com.bank.account.service;

import com.bank.account.dto.DepositRequestDto;
import com.bank.account.dto.TransferRequestDto;
import com.bank.account.dto.WithdrawRequestDto;
import com.bank.account.entity.Account;
import com.bank.account.entity.Transaction;
import com.bank.account.entity.TransactionType;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public List<Account> getAccountsByCustomer(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    public String deposit(
            DepositRequestDto dto) {

        Account account =
                accountRepository
                        .findById(dto.getAccountId())
                        .orElseThrow();

        account.setBalance(
                account.getBalance()
                        .add(dto.getAmount()));

        accountRepository.save(account);

        transactionRepository.save(
                Transaction.builder()
                        .accountId(account.getId())
                        .type(TransactionType.DEPOSIT)
                        .amount(dto.getAmount())
                        .description("Deposit")
                        .createdAt(LocalDateTime.now())
                        .build());

        return "Deposit Successful";
    }

    public String withdraw(
            WithdrawRequestDto dto) {

        Account account =
                accountRepository
                        .findById(dto.getAccountId())
                        .orElseThrow();

        if(account.getBalance()
                .compareTo(dto.getAmount()) < 0) {

            throw new RuntimeException(
                    "Insufficient Balance");
        }

        account.setBalance(
                account.getBalance()
                        .subtract(dto.getAmount()));

        accountRepository.save(account);

        transactionRepository.save(
                Transaction.builder()
                        .accountId(account.getId())
                        .type(TransactionType.WITHDRAW)
                        .amount(dto.getAmount())
                        .description("Withdraw")
                        .createdAt(LocalDateTime.now())
                        .build());

        return "Withdraw Successful";
    }

    public String transfer(
            TransferRequestDto dto) {

        Account from =
                accountRepository
                        .findById(dto.getFromAccountId())
                        .orElseThrow();

        Account to =
                accountRepository
                        .findById(dto.getToAccountId())
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Destination account not found"
                                )
                        );

        if(from.getBalance()
                .compareTo(dto.getAmount()) < 0) {

            throw new RuntimeException(
                    "Insufficient Balance");
        }

        from.setBalance(
                from.getBalance()
                        .subtract(dto.getAmount()));

        to.setBalance(
                to.getBalance()
                        .add(dto.getAmount()));

        accountRepository.save(from);
        accountRepository.save(to);

        return "Transfer Successful";
    }
}
