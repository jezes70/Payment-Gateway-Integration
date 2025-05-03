package com.cyngofokglobal.PaymentGatewayIntegration.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefundRequestDTO {

    private String paymentId;

    private BigDecimal amount;

    private String reason;
}
