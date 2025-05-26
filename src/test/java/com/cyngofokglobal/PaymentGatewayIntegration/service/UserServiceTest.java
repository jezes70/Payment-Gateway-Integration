package com.cyngofokglobal.PaymentGatewayIntegration.service;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.UserRegistrationDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void registerUser_ShouldSaveSuccessfully() {
        UserRegistrationDTO dto = new UserRegistrationDTO();
        dto.setEmail("admin.users@example.com");
        dto.setUsername("admin_users");
        dto.setPassword("admin12345567");

        User user = userService.registerUser(dto);
        assertEquals(dto.getUsername(), user.getUsername());
    }
}
