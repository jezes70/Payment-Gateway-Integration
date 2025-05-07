package com.cyngofokglobal.PaymentGatewayIntegration.controller;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.RefundRequestDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.service.RefundService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/refunds")
public class RefundController {

    private final RefundService refundService;

    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }

    @PostMapping
    public ResponseEntity<String> refundPayment(@Valid @RequestBody RefundRequestDTO request) {
        refundService.processRefund(request);
        return ResponseEntity.ok("Refund processed successfully");
    }
}
