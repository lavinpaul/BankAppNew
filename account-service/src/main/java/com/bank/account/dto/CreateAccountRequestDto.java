package com.bank.account.dto;

import com.bank.account.entity.AccountType;
import lombok.Data;

@Data
public class CreateAccountRequestDto {

    private Long customerId;

    private AccountType accountType;
}