package com.medicallab.council.domain;

import static com.medicallab.council.domain.PractitionerTestSamples.getPractitionerRandomSampleGenerator;
import static com.medicallab.council.domain.PractitionerTestSamples.getPractitionerSample1;
import static com.medicallab.council.domain.PractitionerTestSamples.getPractitionerSample2;
import static com.medicallab.council.domain.QualificationTestSamples.getQualificationRandomSampleGenerator;
import static org.assertj.core.api.Assertions.assertThat;

import com.medicallab.council.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PractitionerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Practitioner.class);
        Practitioner practitioner1 = getPractitionerSample1();
        Practitioner practitioner2 = new Practitioner();
        assertThat(practitioner1).isNotEqualTo(practitioner2);

        practitioner2.setId(practitioner1.getId());
        assertThat(practitioner1).isEqualTo(practitioner2);

        practitioner2 = getPractitionerSample2();
        assertThat(practitioner1).isNotEqualTo(practitioner2);
    }

    @Test
    void qualificationTest() {
        Practitioner practitioner = getPractitionerRandomSampleGenerator();
        Qualification qualificationBack = getQualificationRandomSampleGenerator();
        /*
         * practitioner.qualifications(new HashSet<>(Set.of(qualificationBack)));
         * assertThat(practitioner.getQualifications()).containsOnly(qualificationBack);
         * assertThat(qualificationBack.getPractitioner()).isEqualTo(practitioner);
         *
         * practitioner.setQualifications(new HashSet<>());
         * assertThat(practitioner.getQualifications()).doesNotContain(qualificationBack
         * ); assertThat(qualificationBack.getPractitioner()).isNull();
         */
    }
}
