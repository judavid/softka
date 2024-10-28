package com.accountmovementservice.accountmovementservice.dtos.movement;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MovementResponse {

    private Integer movementId;
    private LocalDateTime date;
    private String type;
    private BigDecimal balance;
    private Integer accountId;

}
