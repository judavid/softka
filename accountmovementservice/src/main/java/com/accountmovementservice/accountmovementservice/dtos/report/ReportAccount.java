package com.accountmovementservice.accountmovementservice.dtos.report;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class ReportAccount {
    private String accountNumber;
    private String accountType;
    private BigDecimal initialBalance;
    private Boolean state;
    private List<ReportMovements> movements;
}
