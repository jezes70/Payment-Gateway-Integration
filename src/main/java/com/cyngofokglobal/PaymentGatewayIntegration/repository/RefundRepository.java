package com.cyngofokglobal.PaymentGatewayIntegration.repository;

import com.cyngofokglobal.PaymentGatewayIntegration.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<Refund, Long> {
}
