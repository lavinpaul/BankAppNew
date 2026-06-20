package com.bank.customer.dto;

import lombok.Data;

@Data
public class CreateCustomerRequest {

    private Long userId;
    private String fullName;
    private String email;
    private String phone;
    private String address;
}