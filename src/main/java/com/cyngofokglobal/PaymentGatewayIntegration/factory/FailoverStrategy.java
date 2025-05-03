package com.cyngofokglobal.PaymentGatewayIntegration.factory;

import java.util.List;

public interface FailoverStrategy {

    List<String> getFailoverSequence(String primaryProvider);
}
