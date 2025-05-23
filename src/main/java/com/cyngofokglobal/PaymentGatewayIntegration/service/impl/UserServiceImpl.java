package com.cyngofokglobal.PaymentGatewayIntegration.service.impl;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.UserRegistrationDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.entity.Role;
import com.cyngofokglobal.PaymentGatewayIntegration.entity.User;
import com.cyngofokglobal.PaymentGatewayIntegration.enums.RoleType;
import com.cyngofokglobal.PaymentGatewayIntegration.repository.RoleRepository;
import com.cyngofokglobal.PaymentGatewayIntegration.repository.UserRepository;
import com.cyngofokglobal.PaymentGatewayIntegration.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User registerUser(UserRegistrationDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("User already exists.");
        }

        Role defaultRole = roleRepository.findByName(RoleType.CUSTOMER)
                .orElseThrow(()-> new RuntimeException("Default role not found"));

        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Collections.singleton(defaultRole))
                .build();

        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("User not found"));
    }
}
