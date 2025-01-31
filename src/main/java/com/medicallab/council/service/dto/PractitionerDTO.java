package com.medicallab.council.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.medicallab.council.domain.Qualification;
import com.medicallab.council.domain.enumeration.AreaOfEmployment;
import com.medicallab.council.domain.enumeration.EmploymentStatus;
import com.medicallab.council.domain.enumeration.Gender;
import com.medicallab.council.domain.enumeration.MaritalStatus;
import com.medicallab.council.domain.enumeration.PractitionerType;
import com.medicallab.council.domain.enumeration.Province;
import com.medicallab.council.domain.enumeration.Title;
import com.medicallab.council.domain.enumeration.TypeOfInstitution;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DTO representing a practitioner, with only the public attributes.
 */
public class PractitionerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private PractitionerType practitionerType;

    private String registrationNumber;

    private Title title;

    private String surname;

    private String forenames;

    private String previousSurname;

    private LocalDate dob;

    private Gender gender;

    private String placeOfBirthTown;

    private String placeOfBirthCountry;

    private String nationality;

    private String nationalId;

    private MaritalStatus maritalStatus;

    private String residentialAddress1;

    private String residentialAddress2;

    private String residentialAddress3;

    private String homePhone;

    private String workPhone;

    private String cellPhone;

    private String emailAddress;

    private String nameOfPlaceOfEmployment;

    private String employerAddress;

    private String employerEmail;

    private String dateOfEmployment;

    private AreaOfEmployment areaOfEmployment;

    private EmploymentStatus employmentStatus;

    private TypeOfInstitution typeOfInstitution;

    private Province provinceEmployed;

    private String reasonForNonEmployment;

    private String dateOfApplication;

    private String applicationFee;

    private String status;

    private String reasonNotApproved;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "practitioner")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "practitioner" }, allowSetters = true)
    private Set<Qualification> qualifications = new HashSet<>();
}
