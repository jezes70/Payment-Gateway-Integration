package com.cyngofokglobal.PaymentGatewayIntegration.factory;

import com.cyngofokglobal.PaymentGatewayIntegration.provider.PaymentProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PaymentProviderFactory {

    private final Map<String, PaymentProvider> providerMap;

    public PaymentProvider getProvider(String name) {
        if (!providerMap.containsKey(name)) {
            throw new RuntimeException("Payment provider not found: " + name);
        }
        return providerMap.get(name);
    }

    public PaymentProviderFactory(List<PaymentProvider> providers) {
        this.providerMap = providers.stream()
                .collect(Collectors.toMap(PaymentProvider::getName, p -> p));
    }
}
