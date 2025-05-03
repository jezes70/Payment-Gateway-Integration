package com.cyngofokglobal.PaymentGatewayIntegration.strategy;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentRequestDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DefaultPaymentRoutingStrategy implements PaymentRoutingStrategy {

    @Override
    public String route(PaymentRequestDTO request) {
        BigDecimal amount = request.getAmount();

        if (amount.compareTo(BigDecimal.valueOf(100)) < 0) {
            return "ProviderA";
        } else if (amount.compareTo(BigDecimal.valueOf(1000)) <= 0) {
            return "ProviderB";

        }else {
            return "ProviderC";
        }
    }
}
