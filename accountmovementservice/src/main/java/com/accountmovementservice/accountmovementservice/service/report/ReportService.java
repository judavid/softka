package com.accountmovementservice.accountmovementservice.service.report;

import com.accountmovementservice.accountmovementservice.dtos.report.ReportResponse;

import java.time.LocalDateTime;

public interface ReportService {

    ReportResponse generateReport(Integer clientId, LocalDateTime from, LocalDateTime to);
}
