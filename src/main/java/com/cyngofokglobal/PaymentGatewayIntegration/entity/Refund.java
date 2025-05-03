package com.cyngofokglobal.PaymentGatewayIntegration.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "refunds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reason;

    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private  Payment payment;

    private LocalDateTime refundedAt;
}
