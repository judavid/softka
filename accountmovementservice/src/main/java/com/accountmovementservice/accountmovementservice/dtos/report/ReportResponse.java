package com.accountmovementservice.accountmovementservice.dtos.report;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ReportResponse {

    private String clientName;
    private String identification;
    private String address;
    private String phone;

    private List<ReportAccount> account;
}
