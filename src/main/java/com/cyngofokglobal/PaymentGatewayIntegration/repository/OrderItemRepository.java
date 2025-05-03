package com.cyngofokglobal.PaymentGatewayIntegration.repository;

import com.cyngofokglobal.PaymentGatewayIntegration.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
