package com.accountmovementservice.accountmovementservice.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account {

    public Account() {
    }

    public Account( String accountNumber, String accountType, BigDecimal initialBalance, Boolean state, Integer clientId) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.state = state;
        this.clientId = clientId;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer accountId;
    private String accountNumber;
    private String accountType;
    private BigDecimal initialBalance;
    private Boolean state;

    @OneToMany(mappedBy = "account")
    private List<Movements> movements;

    @Column(name = "client_id")
    private Integer clientId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(getAccountId(), account.getAccountId()) && Objects.equals(getAccountNumber(), account.getAccountNumber()) && Objects.equals(getAccountType(), account.getAccountType()) && Objects.equals(getInitialBalance(), account.getInitialBalance()) && Objects.equals(getState(), account.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountId(), getAccountNumber(), getAccountType(), getInitialBalance(), getState());
    }
}
