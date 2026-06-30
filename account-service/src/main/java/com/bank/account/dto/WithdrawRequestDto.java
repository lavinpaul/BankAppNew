package com.bank.account.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawRequestDto {

    @NotNull
    private Long accountId;

    @Positive
    private BigDecimal amount;
}