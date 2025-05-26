package com.cyngofokglobal.PaymentGatewayIntegration.service;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentRequestDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Import(SecurityException.class)
public class PaymentServiceTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    PaymentService paymentService;

    @Test
    void processPayment_ShouldReturnResponse() {
        PaymentRequestDTO dto = new PaymentRequestDTO();
        dto.setMerchantId("merchant-12345");
        dto.setCurrency("USD");
        dto.setAmount(new BigDecimal("200.00"));
        dto.setCustomerCountry("US");

        PaymentResponseDTO responseDTO = PaymentService.processPayment(dto);

        assertNotNull(responseDTO);
        assertEquals("SUCCESS", responseDTO.getStatus());
    }
}
