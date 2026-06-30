package com.bank.account.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Data
public class DepositRequestDto {

    @NotNull
    private Long accountId;

    @Positive
    private BigDecimal amount;
}