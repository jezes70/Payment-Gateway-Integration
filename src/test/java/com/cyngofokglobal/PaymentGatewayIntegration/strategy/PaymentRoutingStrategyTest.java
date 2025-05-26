package com.cyngofokglobal.PaymentGatewayIntegration.strategy;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PaymentRoutingStrategyTest {

    @Autowired
    PaymentRoutingStrategy paymentRoutingStrategy;

    @Test
    void route_ShouldReturnProvider() {
        PaymentRequestDTO dto = new PaymentRequestDTO();
        dto.setAmount(new BigDecimal("50"));
        dto.setCustomerCountry("US");

        String provider = paymentRoutingStrategy.route(dto);
        assertNotNull(provider);
    }
}
