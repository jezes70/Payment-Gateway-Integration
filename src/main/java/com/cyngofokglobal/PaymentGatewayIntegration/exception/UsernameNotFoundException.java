package com.cyngofokglobal.PaymentGatewayIntegration.exception;

public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException(String userId) {
        super("User with ID " + userId + "not found");
    }
}
