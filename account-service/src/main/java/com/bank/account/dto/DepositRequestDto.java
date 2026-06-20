package com.bank.account.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequestDto {

    private Long accountId;

    private BigDecimal amount;
}