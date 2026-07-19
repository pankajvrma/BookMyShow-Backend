package com.cfs.bms.service;

import com.cfs.bms.dto.PaymentDto;
import com.cfs.bms.exception.ResourceNotFoundException;
import com.cfs.bms.model.Payment;
import com.cfs.bms.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentDto createPayment(PaymentDto paymentDto) {

        Payment payment = new Payment();

        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setPaymentTime(LocalDateTime.now());
        payment.setStatus(paymentDto.getStatus());
        payment.setTransactionId(UUID.randomUUID().toString());

        Payment savedPayment = paymentRepository.save(payment);

        return mapToDto(savedPayment);
    }

    public PaymentDto getPaymentById(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment Not Found"));

        return mapToDto(payment);
    }

    public List<PaymentDto> getAllPayments() {

        return paymentRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private PaymentDto mapToDto(Payment payment) {

        PaymentDto dto = new PaymentDto();

        dto.setId(payment.getId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setPaymentTime(payment.getPaymentTime());
        dto.setStatus(payment.getStatus());
        dto.setTransactionId(payment.getTransactionId());

        return dto;
    }
}