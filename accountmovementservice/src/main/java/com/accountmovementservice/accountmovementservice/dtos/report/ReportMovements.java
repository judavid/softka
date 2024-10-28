package com.accountmovementservice.accountmovementservice.dtos.report;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ReportMovements {
    private LocalDateTime date;
    private String type;
    private BigDecimal balance;
}
