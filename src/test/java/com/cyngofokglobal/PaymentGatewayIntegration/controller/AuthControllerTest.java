package com.cyngofokglobal.PaymentGatewayIntegration.controller;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.UserRegistrationDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser
    void registerUser_ShouldReturn200_WhenValidRequest()throws Exception{
        UserRegistrationDTO dto = new UserRegistrationDTO();
        dto.setUsername("admin_users");
        dto.setPassword("admin12345567");
        dto.setEmail("admin.users@example.com");

        String jsonPayload = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk());

    }
}
