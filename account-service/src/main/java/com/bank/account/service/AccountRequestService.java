package com.bank.account.service;

import com.bank.account.dto.CreateAccountRequestDto;
import com.bank.account.entity.Account;
import com.bank.account.entity.AccountRequest;
import com.bank.account.entity.AccountRequestStatus;
import com.bank.account.entity.AccountStatus;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.AccountRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountRequestService {

    private final AccountRepository accountRepository;
    private final AccountRequestRepository repository;

    public AccountRequest createRequest(
            CreateAccountRequestDto dto) {

        AccountRequest request =
                AccountRequest.builder()
                        .customerId(dto.getCustomerId())
                        .accountType(dto.getAccountType())
                        .status(AccountRequestStatus.PENDING)
                        .createdAt(LocalDateTime.now())
                        .build();

        return repository.save(request);
    }

    public List<AccountRequest> getCustomerRequests(
            Long customerId) {

        return repository.findByCustomerId(customerId);
    }

    public List<AccountRequest> getPendingRequests() {

        return repository.findByStatus(
                AccountRequestStatus.PENDING);
    }

    public String approveRequest(Long requestId) {

        AccountRequest request =
                repository.findById(requestId)
                        .orElseThrow();

        request.setStatus(
                AccountRequestStatus.APPROVED);

        repository.save(request);

        Account account =
                Account.builder()
                        .customerId(
                                request.getCustomerId())
                        .accountType(
                                request.getAccountType())
                        .accountNumber(
                                generateAccountNumber())
                        .balance(
                                BigDecimal.ZERO)
                        .status(
                                AccountStatus.ACTIVE)
                        .createdAt(
                                LocalDateTime.now())
                        .build();

        accountRepository.save(account);

        return "Account Created Successfully";
    }

    public String rejectRequest(Long requestId) {

        AccountRequest request =
                repository.findById(requestId)
                        .orElseThrow();

        request.setStatus(
                AccountRequestStatus.REJECTED);

        repository.save(request);

        return "Request Rejected";
    }

    private String generateAccountNumber() {

        return String.valueOf(
                1000000000L +
                        (long)(Math.random() * 900000000L));
    }


}