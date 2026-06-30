package com.bank.account.controller;

import com.bank.account.dto.CreateAccountRequestDto;
import com.bank.account.entity.AccountRequest;
import com.bank.account.service.AccountRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account-requests")
@RequiredArgsConstructor
public class AccountRequestController {

    private final AccountRequestService service;

    @PostMapping
    public AccountRequest createRequest(
           @Valid @RequestBody CreateAccountRequestDto dto) {

        return service.createRequest(dto);
    }

    @GetMapping("/customer/{customerId}")
    public List<AccountRequest> getRequests(
            @PathVariable Long customerId) {

        return service.getCustomerRequests(customerId);
    }

    @GetMapping("/pending")
    public List<AccountRequest> getPendingRequests() {

        return service.getPendingRequests();
    }

    @PostMapping("/{id}/reject")
    public String rejectRequest(
            @PathVariable Long id) {

        return service.rejectRequest(id);
    }

    @PostMapping("/{id}/approve")
    public String approveRequest(
            @PathVariable Long id) {

        return service.approveRequest(id);
    }
}