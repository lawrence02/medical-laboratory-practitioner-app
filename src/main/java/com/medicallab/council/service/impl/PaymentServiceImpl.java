package com.medicallab.council.service.impl;

import com.medicallab.council.domain.Payment;
import com.medicallab.council.repository.PaymentRepository;
import com.medicallab.council.service.PaymentService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.medicallab.council.domain.Payment}.
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        LOG.debug("Request to save Payment : {}", payment);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment update(Payment payment) {
        LOG.debug("Request to update Payment : {}", payment);
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> partialUpdate(Payment payment) {
        LOG.debug("Request to partially update Payment : {}", payment);

        return paymentRepository
            .findById(payment.getId())
            .map(existingPayment -> {
                if (payment.getRegistrationNumber() != null) {
                    existingPayment.setRegistrationNumber(payment.getRegistrationNumber());
                }
                if (payment.getDatePaid() != null) {
                    existingPayment.setDatePaid(payment.getDatePaid());
                }
                if (payment.getSubscriptionPeriod() != null) {
                    existingPayment.setSubscriptionPeriod(payment.getSubscriptionPeriod());
                }
                if (payment.getAmount() != null) {
                    existingPayment.setAmount(payment.getAmount());
                }

                return existingPayment;
            })
            .map(paymentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Payment> findAll(Pageable pageable) {
        LOG.debug("Request to get all Payments");
        return paymentRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Payment> findOne(Long id) {
        LOG.debug("Request to get Payment : {}", id);
        return paymentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Payment : {}", id);
        paymentRepository.deleteById(id);
    }
}
