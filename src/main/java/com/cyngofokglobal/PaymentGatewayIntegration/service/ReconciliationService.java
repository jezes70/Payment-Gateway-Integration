package com.cyngofokglobal.PaymentGatewayIntegration.service;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.ReconciliationReportDTO;

public interface ReconciliationService {
    ReconciliationReportDTO generateReport(String currency);
}
