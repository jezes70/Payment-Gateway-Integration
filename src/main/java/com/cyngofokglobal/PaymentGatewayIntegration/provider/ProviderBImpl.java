package com.cyngofokglobal.PaymentGatewayIntegration.provider;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentRequestDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class ProviderBImpl implements PaymentProvider {

    @Override
    public PaymentResponseDTO process(PaymentRequestDTO request) {
        return PaymentResponseDTO.builder()
                .paymentId(UUID.randomUUID().toString())
                .status("SUCCESS")
                .provider(getName())
                .fee(request.getAmount().multiply(BigDecimal.valueOf(0.025)))
                .build();
    }

    @Override
    public String getName() {
        return "ProviderB";
    }
}
