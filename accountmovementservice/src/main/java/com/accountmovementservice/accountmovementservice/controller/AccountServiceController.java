package com.accountmovementservice.accountmovementservice.controller;

import com.accountmovementservice.accountmovementservice.dtos.account.AccountBody;
import com.accountmovementservice.accountmovementservice.dtos.account.AccountResponse;
import com.accountmovementservice.accountmovementservice.dtos.Response;
import com.accountmovementservice.accountmovementservice.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountServiceController {


    private final AccountService accountService;

    @Autowired
    public AccountServiceController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping(value = "/cuenta/{accountId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable(name = "accountId",required = false) Integer accountId) {
        AccountResponse account = accountService.getAccount(accountId);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/cuenta")
    public ResponseEntity<Response> addAccount(@RequestBody AccountBody accountBody) {
        boolean created = accountService.createAccount(accountBody);
        return created ? ResponseEntity.ok(new Response(created, "Created Account")) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/cuenta/{accountId}")
    public ResponseEntity<Response> deleteAccount(@PathVariable(name = "accountId") Integer accountId) {
        boolean deleted = accountService.deleteAccount(accountId);
        return deleted ? ResponseEntity.ok(new Response(deleted, "Deleted Account")) : ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/cuenta/{accountId}")
    public ResponseEntity<Response> updateAccount(@PathVariable(name = "accountId") Integer accountId, @RequestBody AccountBody accountBody) {
        boolean updated = accountService.editAccount(accountBody, accountId);
        return updated ? ResponseEntity.ok(new Response(updated, "Updated Account")) : ResponseEntity.badRequest().build();
    }

}
