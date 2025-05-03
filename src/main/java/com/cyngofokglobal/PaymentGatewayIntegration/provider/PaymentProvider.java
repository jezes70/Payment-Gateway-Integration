package com.cyngofokglobal.PaymentGatewayIntegration.provider;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentRequestDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentResponseDTO;

public interface PaymentProvider {

    PaymentResponseDTO process(PaymentRequestDTO request);
    String getName();
}
