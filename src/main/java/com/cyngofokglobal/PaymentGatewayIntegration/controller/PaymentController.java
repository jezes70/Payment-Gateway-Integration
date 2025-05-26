package com.cyngofokglobal.PaymentGatewayIntegration.controller;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentRequestDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentResponseDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.dto.RefundRequestDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PreAuthorize("hasAnyRole('MERCHANT', 'ADMIN')")
    @PostMapping("/payments")
    public ResponseEntity<PaymentResponseDTO> processPayment(@Valid @RequestBody PaymentRequestDTO request) {
        PaymentResponseDTO response = PaymentService.processPayment(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentStatus(@PathVariable String paymentId) {
        PaymentResponseDTO response = paymentService.getPaymentStatus(paymentId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refund")
    public ResponseEntity<String> refundPayment(@Valid @RequestBody RefundRequestDTO request) {
        paymentService.refundPayment(request);
        return ResponseEntity.ok("Refund process successfully.");
    }
}
