package com.medicallab.council.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class PractitionerTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Practitioner getPractitionerSample1() {
        return new Practitioner()
            .id(1L)
            .registrationNumber("registrationNumber1")
            .surname("surname1")
            .forenames("forenames1")
            .previousSurname("previousSurname1")
            .placeOfBirthTown("placeOfBirthTown1")
            .placeOfBirthCountry("placeOfBirthCountry1")
            .nationality("nationality1")
            .nationalId("nationalId1")
            .residentialAddress1("residentialAddress11")
            .residentialAddress2("residentialAddress21")
            .residentialAddress3("residentialAddress31")
            .homePhone("homePhone1")
            .workPhone("workPhone1")
            .cellPhone("cellPhone1")
            .emailAddress("emailAddress1")
            .nameOfPlaceOfEmployment("nameOfPlaceOfEmployment1")
            .employerAddress("employerAddress1")
            .employerEmail("employerEmail1")
            .dateOfEmployment("dateOfEmployment1")
            .reasonForNonEmployment("reasonForNonEmployment1")
            .dateOfApplication("dateOfApplication1")
            .applicationFee("applicationFee1")
            .status("status1")
            .reasonNotApproved("reasonNotApproved1");
    }

    public static Practitioner getPractitionerSample2() {
        return new Practitioner()
            .id(2L)
            .registrationNumber("registrationNumber2")
            .surname("surname2")
            .forenames("forenames2")
            .previousSurname("previousSurname2")
            .placeOfBirthTown("placeOfBirthTown2")
            .placeOfBirthCountry("placeOfBirthCountry2")
            .nationality("nationality2")
            .nationalId("nationalId2")
            .residentialAddress1("residentialAddress12")
            .residentialAddress2("residentialAddress22")
            .residentialAddress3("residentialAddress32")
            .homePhone("homePhone2")
            .workPhone("workPhone2")
            .cellPhone("cellPhone2")
            .emailAddress("emailAddress2")
            .nameOfPlaceOfEmployment("nameOfPlaceOfEmployment2")
            .employerAddress("employerAddress2")
            .employerEmail("employerEmail2")
            .dateOfEmployment("dateOfEmployment2")
            .reasonForNonEmployment("reasonForNonEmployment2")
            .dateOfApplication("dateOfApplication2")
            .applicationFee("applicationFee2")
            .status("status2")
            .reasonNotApproved("reasonNotApproved2");
    }

    public static Practitioner getPractitionerRandomSampleGenerator() {
        return new Practitioner()
            .id(longCount.incrementAndGet())
            .registrationNumber(UUID.randomUUID().toString())
            .surname(UUID.randomUUID().toString())
            .forenames(UUID.randomUUID().toString())
            .previousSurname(UUID.randomUUID().toString())
            .placeOfBirthTown(UUID.randomUUID().toString())
            .placeOfBirthCountry(UUID.randomUUID().toString())
            .nationality(UUID.randomUUID().toString())
            .nationalId(UUID.randomUUID().toString())
            .residentialAddress1(UUID.randomUUID().toString())
            .residentialAddress2(UUID.randomUUID().toString())
            .residentialAddress3(UUID.randomUUID().toString())
            .homePhone(UUID.randomUUID().toString())
            .workPhone(UUID.randomUUID().toString())
            .cellPhone(UUID.randomUUID().toString())
            .emailAddress(UUID.randomUUID().toString())
            .nameOfPlaceOfEmployment(UUID.randomUUID().toString())
            .employerAddress(UUID.randomUUID().toString())
            .employerEmail(UUID.randomUUID().toString())
            .dateOfEmployment(UUID.randomUUID().toString())
            .reasonForNonEmployment(UUID.randomUUID().toString())
            .dateOfApplication(UUID.randomUUID().toString())
            .applicationFee(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString())
            .reasonNotApproved(UUID.randomUUID().toString());
    }
}
