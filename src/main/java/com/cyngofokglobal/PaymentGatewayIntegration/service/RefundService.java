package com.cyngofokglobal.PaymentGatewayIntegration.service;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.RefundRequestDTO;

public interface RefundService {

    void processRefund(RefundRequestDTO request);
}
