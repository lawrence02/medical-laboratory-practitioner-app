package com.medicallab.council.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class QualificationTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Qualification getQualificationSample1() {
        return new Qualification()
            .id(1L)
            .qualificationName("qualificationName1")
            .nameOfInstitute("nameOfInstitute1")
            .awardedBy("awardedBy1");
    }

    public static Qualification getQualificationSample2() {
        return new Qualification()
            .id(2L)
            .qualificationName("qualificationName2")
            .nameOfInstitute("nameOfInstitute2")
            .awardedBy("awardedBy2");
    }

    public static Qualification getQualificationRandomSampleGenerator() {
        return new Qualification()
            .id(longCount.incrementAndGet())
            .qualificationName(UUID.randomUUID().toString())
            .nameOfInstitute(UUID.randomUUID().toString())
            .awardedBy(UUID.randomUUID().toString());
    }
}
