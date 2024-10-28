package com.accountmovementservice.accountmovementservice.service.account;

import com.accountmovementservice.accountmovementservice.dtos.account.AccountBody;
import com.accountmovementservice.accountmovementservice.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountEntityService {

    Account createAccount(AccountBody account);

    Optional<Account> editAccount(AccountBody accountBody, Integer accountId);

    Account createAccount(Account account);

    boolean deleteAccount(Integer accountId);

    Optional<Account> getAccount(Integer accountId);

    List<Account> getAccounts(Integer clientId);
}
