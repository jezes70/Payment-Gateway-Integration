package com.cyngofokglobal.PaymentGatewayIntegration.service.impl;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.RefundRequestDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.entity.Payment;
import com.cyngofokglobal.PaymentGatewayIntegration.entity.Refund;
import com.cyngofokglobal.PaymentGatewayIntegration.repository.PaymentRepository;
import com.cyngofokglobal.PaymentGatewayIntegration.repository.RefundRepository;
import com.cyngofokglobal.PaymentGatewayIntegration.service.RefundService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
//@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {

    private final PaymentRepository paymentRepository;
    private final RefundRepository refundRepository;

    public RefundServiceImpl(PaymentRepository paymentRepository, RefundRepository refundRepository) {
        this.paymentRepository = paymentRepository;
        this.refundRepository = refundRepository;
    }

    @Override
    @Transactional
    public void processRefund(RefundRequestDTO request) {
        Payment payment = paymentRepository.findByPaymentId(request.getPaymentId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        BigDecimal refundAmount = request.getAmount() != null ? request.getAmount() : payment.getAmount();

        Refund refund = Refund.builder()
                .payment(payment)
                .amount(refundAmount)
                .reason(request.getReason())
                .refundedAt(LocalDateTime.now())
                .build();

        refundRepository.save(refund);

        payment.setStatus("REFUNDED");
        payment.setUpdatedAt(LocalDateTime.now());
        paymentRepository.save(payment);
    }
}
