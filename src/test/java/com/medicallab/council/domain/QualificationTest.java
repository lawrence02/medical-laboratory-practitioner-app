package com.medicallab.council.domain;

import static com.medicallab.council.domain.PractitionerTestSamples.*;
import static com.medicallab.council.domain.QualificationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.medicallab.council.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QualificationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Qualification.class);
        Qualification qualification1 = getQualificationSample1();
        Qualification qualification2 = new Qualification();
        assertThat(qualification1).isNotEqualTo(qualification2);

        qualification2.setId(qualification1.getId());
        assertThat(qualification1).isEqualTo(qualification2);

        qualification2 = getQualificationSample2();
        assertThat(qualification1).isNotEqualTo(qualification2);
    }

    @Test
    void practitionerTest() {
        Qualification qualification = getQualificationRandomSampleGenerator();
        Practitioner practitionerBack = getPractitionerRandomSampleGenerator();

        qualification.setPractitioner(practitionerBack);
        assertThat(qualification.getPractitioner()).isEqualTo(practitionerBack);

        qualification.practitioner(null);
        assertThat(qualification.getPractitioner()).isNull();
    }
}
