package com.bank.account.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequestDto {

    @NotNull
    private Long fromAccountId;

    @NotNull
    private Long toAccountId;

    @Positive
    private BigDecimal amount;
}