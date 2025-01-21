package com.medicallab.council.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class PaymentTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Payment getPaymentSample1() {
        return new Payment().id(1L).registrationNumber("registrationNumber1").subscriptionPeriod("subscriptionPeriod1").amount("amount1");
    }

    public static Payment getPaymentSample2() {
        return new Payment().id(2L).registrationNumber("registrationNumber2").subscriptionPeriod("subscriptionPeriod2").amount("amount2");
    }

    public static Payment getPaymentRandomSampleGenerator() {
        return new Payment()
            .id(longCount.incrementAndGet())
            .registrationNumber(UUID.randomUUID().toString())
            .subscriptionPeriod(UUID.randomUUID().toString())
            .amount(UUID.randomUUID().toString());
    }
}
