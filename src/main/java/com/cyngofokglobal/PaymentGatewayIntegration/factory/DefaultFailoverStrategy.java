package com.cyngofokglobal.PaymentGatewayIntegration.factory;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultFailoverStrategy implements FailoverStrategy {

    private final List<String> providers = Arrays.asList("ProviderA", "ProviderB", "ProviderC");

    @Override
    public List<String> getFailoverSequence(String primaryProvider) {
        return providers.stream()
                .filter(p -> !p.equals(primaryProvider))
                .collect(Collectors.toList());
    }
}
