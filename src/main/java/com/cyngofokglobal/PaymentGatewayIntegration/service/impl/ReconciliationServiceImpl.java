package com.cyngofokglobal.PaymentGatewayIntegration.service.impl;

import com.cyngofokglobal.PaymentGatewayIntegration.dto.ReconciliationReportDTO;
import com.cyngofokglobal.PaymentGatewayIntegration.entity.Payment;
import com.cyngofokglobal.PaymentGatewayIntegration.repository.PaymentRepository;
import com.cyngofokglobal.PaymentGatewayIntegration.service.ReconciliationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReconciliationServiceImpl implements ReconciliationService {

    private final PaymentRepository paymentRepository;

    public ReconciliationServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public ReconciliationReportDTO generateReport(String currency) {
        List<Payment> payments = paymentRepository.findAll().stream()
                .filter(p -> p.getCurrency().equalsIgnoreCase(currency))
                .collect(Collectors.toList());

        Map<String, BigDecimal> totalsByProvider = payments.stream()
                .collect(Collectors.groupingBy(Payment::getPaymentProvider, Collectors.reducing(BigDecimal.ZERO, Payment::getAmount, BigDecimal::add)));

        BigDecimal total = payments.stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return ReconciliationReportDTO.builder()
                .currency(currency.toUpperCase())
                .totalsByProvider(totalsByProvider)
                .totalConvertedToBaseCurrency(total)
                .build();
    }
}
