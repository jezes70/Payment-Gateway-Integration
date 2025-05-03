package com.cyngofokglobal.PaymentGatewayIntegration.dto;

import com.cyngofokglobal.PaymentGatewayIntegration.enums.PaymentMethodType;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDTO {

    private String merchantId;

    private BigDecimal amount;

    private String currency;

    private String customerCountry;

    private PaymentMethod paymentMethod;

    private OrderDetails orderDetails;
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PaymentMethod {
        private PaymentMethodType type;

        private Map<String, Object> details;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OrderDetails {
        private String orderId;

        private List<OrderItemDTO> items;

    }
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OrderItemDTO {
        private String itemId;
        private Integer quantity;

        private BigDecimal unitPrice;
    }
}
