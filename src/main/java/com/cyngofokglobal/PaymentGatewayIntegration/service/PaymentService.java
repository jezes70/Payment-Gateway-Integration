package com.cyngofokglobal.PaymentGatewayIntegration.service;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentRequestDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentResponseDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.dto.RefundRequestDTO;
import jakarta.transaction.Transactional;

public interface PaymentService {

    static PaymentResponseDTO processPayment(PaymentRequestDTO request) {
        return null;
    }
    PaymentResponseDTO getPaymentStatus(String paymentId);
    void refundPayment(RefundRequestDTO request);
}
