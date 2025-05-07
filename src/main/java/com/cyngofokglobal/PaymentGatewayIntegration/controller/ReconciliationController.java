package com.cyngofokglobal.PaymentGatewayIntegration.controller;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.ReconciliationReportDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.service.ReconciliationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reconciliation")
public class ReconciliationController {

    private final ReconciliationService reconciliationService;

    public ReconciliationController(ReconciliationService reconciliationService) {
        this.reconciliationService = reconciliationService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reconciliation")
    public ResponseEntity<ReconciliationReportDTO> generateReport(@RequestParam String currency) {
        ReconciliationReportDTO report = reconciliationService.generateReport(currency);
        return ResponseEntity.ok(report);
    }
}
