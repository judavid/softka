package com.accountmovementservice.accountmovementservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Movements")
@Getter
@Setter
public class Movements {

    public Movements() {
    }

    public Movements( LocalDateTime date, String type, BigDecimal balance, Account account) {
        this.date = date;
        this.type = type;
        this.balance = balance;
        this.account = account;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer movementId;
    private LocalDateTime date;
    private String type;
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movements movements)) return false;
        return Objects.equals(getMovementId(), movements.getMovementId()) && Objects.equals(getDate(), movements.getDate()) && Objects.equals(getType(), movements.getType()) && Objects.equals(getBalance(), movements.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovementId(), getDate(), getType(), getBalance());
    }
}
