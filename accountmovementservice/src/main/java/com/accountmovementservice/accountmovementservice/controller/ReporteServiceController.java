package com.accountmovementservice.accountmovementservice.controller;

import com.accountmovementservice.accountmovementservice.dtos.report.ReportResponse;
import com.accountmovementservice.accountmovementservice.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class ReporteServiceController {


    private final ReportService reportService;

    @Autowired
    public ReporteServiceController(ReportService reportService) {
        this.reportService = reportService;
    }


    @GetMapping(value = "/reportes/{clientId}")
    public ResponseEntity<ReportResponse> getClientReport(@PathVariable(name = "clientId") Integer clientId,
                                                          @RequestParam(name = "from") LocalDateTime from,
                                                          @RequestParam(name = "to") LocalDateTime to) {

        ReportResponse reportResponse = reportService.generateReport(clientId, from, to);
        return reportResponse != null ? ResponseEntity.ok(reportResponse) : ResponseEntity.notFound().build();
    }


}
