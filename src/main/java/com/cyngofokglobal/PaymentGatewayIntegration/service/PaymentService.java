package com.cyngofokglobal.PaymentGatewayIntegration.service;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentRequestDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentResponseDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.dto.RefundRequestDTO;

public interface PaymentService {

    PaymentResponseDTO processPayment(PaymentRequestDTO request);
    PaymentResponseDTO getPaymentStatus(String paymentId);
    void refundPayment(RefundRequestDTO request);
}
