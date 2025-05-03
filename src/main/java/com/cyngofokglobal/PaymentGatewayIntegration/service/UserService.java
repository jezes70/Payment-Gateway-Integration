package com.cyngofokglobal.PaymentGatewayIntegration.service;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.UserRegistrationDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.entity.User;

public interface UserService {

    User registerUser(UserRegistrationDTO dto);
    User getUserByUsername(String username);
}
