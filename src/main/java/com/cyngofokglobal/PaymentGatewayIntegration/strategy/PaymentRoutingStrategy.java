package com.cyngofokglobal.PaymentGatewayIntegration.strategy;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentRequestDTO;

public interface PaymentRoutingStrategy {

    String route(PaymentRequestDTO request);
}
