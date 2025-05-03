package com.cyngofokglobal.PaymentGatewayIntegration.entity;

import com.cyngofokglobal.PaymentGatewayIntegration.enums.PaymentMethodType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentId;

    private String merchantId;

    private BigDecimal amount;

    private String currency;

    private String customerCountry;
    @Enumerated(EnumType.STRING)
    private PaymentMethodType paymentMethodType;

    private String paymentProvider;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
