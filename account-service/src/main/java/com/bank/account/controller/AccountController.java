package com.bank.account.controller;

import com.bank.account.dto.DepositRequestDto;
import com.bank.account.dto.TransferRequestDto;
import com.bank.account.dto.WithdrawRequestDto;
import com.bank.account.entity.Account;
import com.bank.account.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/customer/{customerId}")
    public List<Account> getAccountsByCustomer(
            @PathVariable Long customerId) {

        return accountService.getAccountsByCustomer(customerId);
    }

    @PostMapping("/deposit")
    public String deposit(
            @Valid @RequestBody DepositRequestDto dto) {

        return accountService.deposit(dto);
    }

    @PostMapping("/withdraw")
    public String withdraw(
            @Valid @RequestBody WithdrawRequestDto dto) {

        return accountService.withdraw(dto);
    }

    @PostMapping("/transfer")
    public String transfer(
            @Valid @RequestBody TransferRequestDto dto) {

        return accountService.transfer(dto);
    }
}
