package com.accountmovementservice.accountmovementservice.service.account;

import com.accountmovementservice.accountmovementservice.dtos.account.AccountBody;
import com.accountmovementservice.accountmovementservice.dtos.account.AccountResponse;

public interface AccountService {

    boolean createAccount(AccountBody account);

    boolean editAccount(AccountBody accountResponse, Integer accountId);

    boolean deleteAccount(Integer accountId);

    AccountResponse getAccount(Integer accountId);

}
