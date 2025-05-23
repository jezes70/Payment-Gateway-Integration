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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Map<String, BigDecimal> getTotalsByProvider() {
        return totalsByProvider;
    }

    public void setTotalsByProvider(Map<String, BigDecimal> totalsByProvider) {
        this.totalsByProvider = totalsByProvider;
    }

    public BigDecimal getTotalConvertedToBaseCurrency() {
        return totalConvertedToBaseCurrency;
    }

    public void setTotalConvertedToBaseCurrency(BigDecimal totalConvertedToBaseCurrency) {
        this.totalConvertedToBaseCurrency = totalConvertedToBaseCurrency;
    }


}
