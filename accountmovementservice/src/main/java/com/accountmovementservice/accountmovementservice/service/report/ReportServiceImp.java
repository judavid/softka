package com.accountmovementservice.accountmovementservice.service.report;

import com.accountmovementservice.accountmovementservice.dtos.client.ClientResponse;
import com.accountmovementservice.accountmovementservice.dtos.report.ReportAccount;
import com.accountmovementservice.accountmovementservice.dtos.report.ReportMovements;
import com.accountmovementservice.accountmovementservice.dtos.report.ReportResponse;
import com.accountmovementservice.accountmovementservice.model.Account;
import com.accountmovementservice.accountmovementservice.service.Client.ClientService;
import com.accountmovementservice.accountmovementservice.service.Movement.MovementService;
import com.accountmovementservice.accountmovementservice.service.account.AccountEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportServiceImp implements ReportService {

    private final ClientService clientService;

    private final AccountEntityService accountEntityService;
    private final MovementService movementService;

    @Autowired
    public ReportServiceImp(ClientService clientService, AccountEntityService accountEntityService, MovementService movementService) {
        this.clientService = clientService;
        this.accountEntityService = accountEntityService;
        this.movementService = movementService;
    }

    @Override
    public ReportResponse generateReport(Integer clientId, LocalDateTime from, LocalDateTime to) {
        ClientResponse client = clientService.getClient(clientId);
        ReportResponse.ReportResponseBuilder reportResponseBuilder = setClientInfo(client);
        List<Account> accounts = accountEntityService.getAccounts(clientId);
        reportResponseBuilder.account(accounts.stream().map(account -> getAccount(account,from, to)).toList());
        return reportResponseBuilder.build();
    }

    private static ReportResponse.ReportResponseBuilder setClientInfo(ClientResponse client) {
        return ReportResponse.builder().clientName(client.getName())
                .identification(client.getIdentification())
                .phone(client.getPhone())
                .address(client.getAddress());
    }

    private ReportAccount getAccount(Account accountEnt, LocalDateTime from, LocalDateTime to) {
        return ReportAccount.builder()
                .accountNumber(accountEnt.getAccountNumber())
                .initialBalance(accountEnt.getInitialBalance())
                .accountType(accountEnt.getAccountType())
                .state(accountEnt.getState())
                .movements(getReportMovements(accountEnt, from, to))
                .build();
    }

    private List<ReportMovements> getReportMovements(Account accountEnt, LocalDateTime from, LocalDateTime to) {
        return movementService.getMovements(accountEnt.getAccountId(), from, to).stream().map(mov ->
                ReportMovements.builder().date(mov.getDate())
                        .type(mov.getType())
                        .balance(mov.getBalance())
                        .build()
        ).toList();
    }
}
