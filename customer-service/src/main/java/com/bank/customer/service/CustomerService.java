package com.bank.customer.service;

import com.bank.customer.dto.CreateCustomerRequest;
import com.bank.customer.entity.Customer;
import com.bank.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer createCustomer(
            CreateCustomerRequest request) {

        Customer customer = Customer.builder()
                .userId(request.getUserId())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .createdAt(LocalDateTime.now())
                .build();

        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long userId) {
        return customerRepository
                .findByUserId(userId)
                .orElseThrow(
                        () -> new RuntimeException("Customer not found"));
    }
}