package com.cyngofokglobal.PaymentGatewayIntegration.repository;

import com.cyngofokglobal.PaymentGatewayIntegration.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByPaymentId(String paymentId);
}
