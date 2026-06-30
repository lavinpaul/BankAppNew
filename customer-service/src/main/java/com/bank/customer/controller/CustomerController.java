package com.bank.customer.controller;

import com.bank.customer.dto.CreateCustomerRequest;
import com.bank.customer.entity.Customer;
import com.bank.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Customer createCustomer(
            @Valid @RequestBody CreateCustomerRequest request) {

        return customerService.createCustomer(request);
    }

    @GetMapping("/{userId}")
    public Customer getCustomer(
            @PathVariable Long userId) {

        return customerService.getCustomer(userId);
    }
}