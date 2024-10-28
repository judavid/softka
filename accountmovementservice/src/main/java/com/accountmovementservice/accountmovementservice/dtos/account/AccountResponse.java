package com.accountmovementservice.accountmovementservice.dtos.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class AccountResponse {

    private Integer accountId;
    private String accountNumber;
    private String accountType;
    private BigDecimal initialBalance;
    private Boolean state;
    private Integer clientId;

}
