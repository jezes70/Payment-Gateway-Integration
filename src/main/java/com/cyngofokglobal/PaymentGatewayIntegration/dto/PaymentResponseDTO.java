package com.cyngofokglobal.PaymentGatewayIntegration.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDTO {

    private String paymentId;

    private String status;

    private String provider;

    private BigDecimal fee;
}
