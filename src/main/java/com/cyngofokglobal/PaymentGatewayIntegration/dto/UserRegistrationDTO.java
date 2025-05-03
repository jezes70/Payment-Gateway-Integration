package com.cyngofokglobal.PaymentGatewayIntegration.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationDTO {

    private String username;

    private String password;

    private String email;

}
