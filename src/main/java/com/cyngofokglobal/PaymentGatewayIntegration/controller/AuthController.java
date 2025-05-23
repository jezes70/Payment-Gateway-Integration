package com.cyngofokglobal.PaymentGatewayIntegration.controller;

import com.cyngofokglobal.PaymentGatewayIntegration.config.JwtTokenProvider;
import com.cyngofokglobal.PaymentGatewayIntegration.dto.JwtAuthResponseDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.dto.UserLoginDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.dto.UserRegistrationDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.entity.Role;
import com.cyngofokglobal.PaymentGatewayIntegration.entity.User;
import com.cyngofokglobal.PaymentGatewayIntegration.enums.RoleType;
import com.cyngofokglobal.PaymentGatewayIntegration.exception.RoleNotFoundException;
import com.cyngofokglobal.PaymentGatewayIntegration.exception.UsernameNotFoundException;
import com.cyngofokglobal.PaymentGatewayIntegration.repository.RoleRepository;
import com.cyngofokglobal.PaymentGatewayIntegration.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
@RestControllerAdvice
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository, RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }

        Role defaultRole = roleRepository.findByName(RoleType.CUSTOMER)
                .orElseThrow(() -> new RoleNotFoundException("Default role not found"));

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .roles(Collections.singleton(defaultRole))
                .build();

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDTO> login(@Valid @RequestBody UserLoginDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtTokenProvider.generateToken(
                user.getUsername(),
                user.getRoles().stream()
                        .map(role -> role.getName().name())
                        .toList()
        );

        return ResponseEntity.ok(JwtAuthResponseDTO.builder()
                        .token(token)
                        .username(user.getUsername())
                        .roles(user.getRoles().stream().map(r -> r.getName().name()).toArray(String[]::new))
                .build());
    }

}