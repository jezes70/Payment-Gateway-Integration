package com.cyngofokglobal.PaymentGatewayIntegration.controller;

import com.cyngofokglobal.PaymentGatewayIntegration.config.JwtTokenProvider;
import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PaymentControllerTest.class)
@Import(SecurityException.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private JwtTokenProvider jwtTokenProvider;

    @MockitoBean
    private AuthenticationManager authenticationManager;

    @Test
    @WithMockUser(username = "admin", roles = {"MERCHANT"})
    void initiatePayment_ShouldReturnSuccess() throws Exception {
        PaymentRequestDTO dto = new PaymentRequestDTO();
        dto.setMerchantId("merchant-12345");
        dto.setAmount(new BigDecimal("150.00"));
        dto.setCurrency("USD");
        dto.setCustomerCountry("US");

        mockMvc.perform(post("/api/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
                .header("Authorization", "Bearer mock-token"))
                .andExpect(status().isOk());

    }
}
