package com.bank.account.dto;

import com.bank.account.entity.AccountType;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class CreateAccountRequestDto {

    @NotNull
    private Long customerId;

    @NotNull
    private AccountType accountType;
}