package com.accountmovementservice.accountmovementservice.service.account;

import com.accountmovementservice.accountmovementservice.dtos.account.AccountBody;
import com.accountmovementservice.accountmovementservice.dtos.account.AccountResponse;
import com.accountmovementservice.accountmovementservice.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImp implements AccountService {


    private final AccountEntityService accountEntityService;

    @Autowired
    public AccountServiceImp(AccountEntityService accountEntityService) {
        this.accountEntityService = accountEntityService;
    }

    @Override
    public boolean createAccount(AccountBody account) {
        return accountEntityService.createAccount(account) != null;
    }

    @Override
    public boolean editAccount(AccountBody accountResponse, Integer accountId) {
        return accountEntityService.editAccount(accountResponse, accountId).isPresent();
    }

    @Override
    public boolean deleteAccount(Integer accountId) {
        return accountEntityService.deleteAccount(accountId);
    }

    @Override
    public AccountResponse getAccount(Integer accountId) {
        Optional<Account> accountEnt = accountEntityService.getAccount(accountId);
        return accountEnt.map(account -> AccountResponse.builder().accountNumber(account.getAccountNumber())
                .initialBalance(account.getInitialBalance())
                .accountType(account.getAccountType())
                .state(account.getState())
                .clientId(account.getClientId())
                .accountId(account.getAccountId())
                .build()).orElse(null);
    }
}
