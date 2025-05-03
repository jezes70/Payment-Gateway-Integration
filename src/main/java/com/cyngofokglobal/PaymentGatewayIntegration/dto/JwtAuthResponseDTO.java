package com.cyngofokglobal.PaymentGatewayIntegration.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtAuthResponseDTO {

    private String token;

    private String username;

    private String[] roles;
}
