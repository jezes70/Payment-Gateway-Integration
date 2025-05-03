package com.cyngofokglobal.PaymentGatewayIntegration.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReconciliationReportDTO {

    private String currency;

    private Map<String, BigDecimal> totalsByProvider;

    private BigDecimal totalConvertedToBaseCurrency;
}
