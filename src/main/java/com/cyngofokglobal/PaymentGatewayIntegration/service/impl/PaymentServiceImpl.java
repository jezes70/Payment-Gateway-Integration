package com.cyngofokglobal.PaymentGatewayIntegration.service.impl;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentRequestDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.dto.PaymentResponseDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.dto.RefundRequestDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.entity.Payment;
import com.cyngofokglobal.PaymentGatewayIntegration.exception.PaymentNotFoundException;
import com.cyngofokglobal.PaymentGatewayIntegration.exception.PaymentProcessingException;
import com.cyngofokglobal.PaymentGatewayIntegration.factory.FailoverStrategy;
import com.cyngofokglobal.PaymentGatewayIntegration.factory.PaymentProviderFactory;
import com.cyngofokglobal.PaymentGatewayIntegration.provider.PaymentProvider;
import com.cyngofokglobal.PaymentGatewayIntegration.repository.PaymentRepository;
import com.cyngofokglobal.PaymentGatewayIntegration.service.PaymentService;
import com.cyngofokglobal.PaymentGatewayIntegration.strategy.PaymentRoutingStrategy;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentProviderFactory paymentProviderFactory;
    private final PaymentRoutingStrategy paymentRoutingStrategy;
    private final PaymentRepository paymentRepository;
    private final FailoverStrategy failoverStrategy;
    private final ModelMapper modelMapper;

    public PaymentServiceImpl(PaymentProviderFactory paymentProviderFactory,
                              PaymentRoutingStrategy paymentRoutingStrategy,
                              PaymentRepository paymentRepository, FailoverStrategy failoverStrategy, ModelMapper modelMapper) {
        this.paymentProviderFactory = paymentProviderFactory;
        this.paymentRoutingStrategy = paymentRoutingStrategy;
        this.paymentRepository = paymentRepository;
        this.failoverStrategy = failoverStrategy;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public PaymentResponseDTO processPayment(PaymentRequestDTO request) {
        String primaryProviderName = paymentRoutingStrategy.route(request);
        List<String> failoverProviders = failoverStrategy.getFailoverSequence(primaryProviderName);

        List<String> allProviders = new ArrayList<>();
        allProviders.add(primaryProviderName);
        allProviders.addAll(failoverProviders);

        RuntimeException lastException = null;

        for (String providerName : allProviders) {
            try {
                PaymentProvider provider = paymentProviderFactory.getProvider(providerName);
                PaymentResponseDTO response = provider.process(request);

                Payment payment = Payment.builder()
                        .paymentId(response.getPaymentId())
                        .merchantId(request.getMerchantId())
                        .amount(request.getAmount())
                        .currency(request.getCurrency())
                        .customerCountry(request.getCustomerCountry())
                        .paymentMethodType(request.getPaymentMethod().getType())
                        .paymentProvider(response.getProvider())
                        .status(response.getStatus())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();

                paymentRepository.save(payment);
                return response;

            } catch (RuntimeException ex) {
                lastException = ex;
            }
        }

        throw new PaymentProcessingException("All payment providers failed", lastException);
    }

    @Override
    public PaymentResponseDTO getPaymentStatus(String paymentId) {
        Payment payment = paymentRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));

        return PaymentResponseDTO.builder()
                .paymentId(payment.getPaymentId())
                .status(payment.getStatus())
                .provider(payment.getPaymentProvider())
                .fee(null)
                .build();
    }

    @Override
    public void refundPayment(RefundRequestDTO request) {
        Payment payment = paymentRepository.findByPaymentId(request.getPaymentId())
                .orElseThrow(() -> new PaymentNotFoundException(request.getPaymentId()));

        payment.setStatus("REFUNDED");
        payment.setUpdatedAt(LocalDateTime.now());
        paymentRepository.save(payment);
    }
}
