package com.accountmovementservice.accountmovementservice.service.account;

import com.accountmovementservice.accountmovementservice.dtos.account.AccountBody;
import com.accountmovementservice.accountmovementservice.model.Account;
import com.accountmovementservice.accountmovementservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountEntityServiceImpl implements AccountEntityService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountEntityServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public Account createAccount(AccountBody account) {
        Account newAccount = new Account(account.getAccountNumber(),
                account.getAccountType(), account.getInitialBalance(),  account.getState(),
                account.getClientId());
        return accountRepository.save(newAccount);
    }

    @Override
    @Transactional
    public Optional<Account> editAccount(AccountBody accountBody, Integer accountId) {
        Optional<Account> optAccount = accountRepository.findById(accountId);
        if(optAccount.isPresent()){
            Account account = optAccount.get();
            account.setAccountNumber(accountBody.getAccountNumber());
            account.setAccountType(accountBody.getAccountType());
            account.setInitialBalance(accountBody.getInitialBalance());
            account.setState(accountBody.getState());
            account.setClientId(accountBody.getClientId());
            return Optional.of(createAccount(account));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public boolean deleteAccount(Integer accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent()){
            accountRepository.delete(account.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<Account> getAccount(Integer accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public List<Account> getAccounts(Integer clientId) {
        return accountRepository.findByClientId(clientId);
    }
}
