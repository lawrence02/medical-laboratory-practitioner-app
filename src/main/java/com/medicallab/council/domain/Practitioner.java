package com.medicallab.council.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.medicallab.council.domain.enumeration.AreaOfEmployment;
import com.medicallab.council.domain.enumeration.EmploymentStatus;
import com.medicallab.council.domain.enumeration.Gender;
import com.medicallab.council.domain.enumeration.MaritalStatus;
import com.medicallab.council.domain.enumeration.PractitionerType;
import com.medicallab.council.domain.enumeration.Province;
import com.medicallab.council.domain.enumeration.Title;
import com.medicallab.council.domain.enumeration.TypeOfInstitution;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Practitioner.
 */
@Entity
@Table(name = "practitioner")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Practitioner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The practitionerId attribute.
     */
    @Schema(description = "The practitionerId attribute.")
    @Enumerated(EnumType.STRING)
    @Column(name = "practitioner_type")
    private PractitionerType practitionerType;

    @NotNull
    @Column(name = "registration_number", nullable = false)
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "title")
    private Title title;

    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotNull
    @Column(name = "forenames", nullable = false)
    private String forenames;

    @Column(name = "previous_surname")
    private String previousSurname;

    @NotNull
    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "place_of_birth_town")
    private String placeOfBirthTown;

    @Column(name = "place_of_birth_country")
    private String placeOfBirthCountry;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "national_id")
    private String nationalId;

    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status")
    private MaritalStatus maritalStatus;

    @Column(name = "residential_address_1")
    private String residentialAddress1;

    @Column(name = "residential_address_2")
    private String residentialAddress2;

    @Column(name = "residential_address_3")
    private String residentialAddress3;

    @Column(name = "home_phone")
    private String homePhone;

    @Column(name = "work_phone")
    private String workPhone;

    @Column(name = "cell_phone")
    private String cellPhone;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "name_of_place_of_employment")
    private String nameOfPlaceOfEmployment;

    @Column(name = "employer_address")
    private String employerAddress;

    @Column(name = "employer_email")
    private String employerEmail;

    @Column(name = "date_of_employment")
    private String dateOfEmployment;

    @Enumerated(EnumType.STRING)
    @Column(name = "area_of_employment")
    private AreaOfEmployment areaOfEmployment;

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_status")
    private EmploymentStatus employmentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_institution")
    private TypeOfInstitution typeOfInstitution;

    @Enumerated(EnumType.STRING)
    @Column(name = "province_employed")
    private Province provinceEmployed;

    @Column(name = "reason_for_non_employment")
    private String reasonForNonEmployment;

    @Column(name = "date_of_application")
    private String dateOfApplication;

    @Column(name = "application_fee")
    private String applicationFee;

    @Column(name = "status")
    private String status;

    @Column(name = "reason_not_approved")
    private String reasonNotApproved;

    @OneToMany(mappedBy = "practitioner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "practitioner" }, allowSetters = true)
    private Set<Qualification> qualifications = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Practitioner id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PractitionerType getPractitionerType() {
        return this.practitionerType;
    }

    public Practitioner practitionerType(PractitionerType practitionerType) {
        this.setPractitionerType(practitionerType);
        return this;
    }

    public void setPractitionerType(PractitionerType practitionerType) {
        this.practitionerType = practitionerType;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public Practitioner registrationNumber(String registrationNumber) {
        this.setRegistrationNumber(registrationNumber);
        return this;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Title getTitle() {
        return this.title;
    }

    public Practitioner title(Title title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getSurname() {
        return this.surname;
    }

    public Practitioner surname(String surname) {
        this.setSurname(surname);
        return this;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getForenames() {
        return this.forenames;
    }

    public Practitioner forenames(String forenames) {
        this.setForenames(forenames);
        return this;
    }

    public void setForenames(String forenames) {
        this.forenames = forenames;
    }

    public String getPreviousSurname() {
        return this.previousSurname;
    }

    public Practitioner previousSurname(String previousSurname) {
        this.setPreviousSurname(previousSurname);
        return this;
    }

    public void setPreviousSurname(String previousSurname) {
        this.previousSurname = previousSurname;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public Practitioner dob(LocalDate dob) {
        this.setDob(dob);
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return this.gender;
    }

    public Practitioner gender(Gender gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPlaceOfBirthTown() {
        return this.placeOfBirthTown;
    }

    public Practitioner placeOfBirthTown(String placeOfBirthTown) {
        this.setPlaceOfBirthTown(placeOfBirthTown);
        return this;
    }

    public void setPlaceOfBirthTown(String placeOfBirthTown) {
        this.placeOfBirthTown = placeOfBirthTown;
    }

    public String getPlaceOfBirthCountry() {
        return this.placeOfBirthCountry;
    }

    public Practitioner placeOfBirthCountry(String placeOfBirthCountry) {
        this.setPlaceOfBirthCountry(placeOfBirthCountry);
        return this;
    }

    public void setPlaceOfBirthCountry(String placeOfBirthCountry) {
        this.placeOfBirthCountry = placeOfBirthCountry;
    }

    public String getNationality() {
        return this.nationality;
    }

    public Practitioner nationality(String nationality) {
        this.setNationality(nationality);
        return this;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationalId() {
        return this.nationalId;
    }

    public Practitioner nationalId(String nationalId) {
        this.setNationalId(nationalId);
        return this;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public MaritalStatus getMaritalStatus() {
        return this.maritalStatus;
    }

    public Practitioner maritalStatus(MaritalStatus maritalStatus) {
        this.setMaritalStatus(maritalStatus);
        return this;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getResidentialAddress1() {
        return this.residentialAddress1;
    }

    public Practitioner residentialAddress1(String residentialAddress1) {
        this.setResidentialAddress1(residentialAddress1);
        return this;
    }

    public void setResidentialAddress1(String residentialAddress1) {
        this.residentialAddress1 = residentialAddress1;
    }

    public String getResidentialAddress2() {
        return this.residentialAddress2;
    }

    public Practitioner residentialAddress2(String residentialAddress2) {
        this.setResidentialAddress2(residentialAddress2);
        return this;
    }

    public void setResidentialAddress2(String residentialAddress2) {
        this.residentialAddress2 = residentialAddress2;
    }

    public String getResidentialAddress3() {
        return this.residentialAddress3;
    }

    public Practitioner residentialAddress3(String residentialAddress3) {
        this.setResidentialAddress3(residentialAddress3);
        return this;
    }

    public void setResidentialAddress3(String residentialAddress3) {
        this.residentialAddress3 = residentialAddress3;
    }

    public String getHomePhone() {
        return this.homePhone;
    }

    public Practitioner homePhone(String homePhone) {
        this.setHomePhone(homePhone);
        return this;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getWorkPhone() {
        return this.workPhone;
    }

    public Practitioner workPhone(String workPhone) {
        this.setWorkPhone(workPhone);
        return this;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getCellPhone() {
        return this.cellPhone;
    }

    public Practitioner cellPhone(String cellPhone) {
        this.setCellPhone(cellPhone);
        return this;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public Practitioner emailAddress(String emailAddress) {
        this.setEmailAddress(emailAddress);
        return this;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getNameOfPlaceOfEmployment() {
        return this.nameOfPlaceOfEmployment;
    }

    public Practitioner nameOfPlaceOfEmployment(String nameOfPlaceOfEmployment) {
        this.setNameOfPlaceOfEmployment(nameOfPlaceOfEmployment);
        return this;
    }

    public void setNameOfPlaceOfEmployment(String nameOfPlaceOfEmployment) {
        this.nameOfPlaceOfEmployment = nameOfPlaceOfEmployment;
    }

    public String getEmployerAddress() {
        return this.employerAddress;
    }

    public Practitioner employerAddress(String employerAddress) {
        this.setEmployerAddress(employerAddress);
        return this;
    }

    public void setEmployerAddress(String employerAddress) {
        this.employerAddress = employerAddress;
    }

    public String getEmployerEmail() {
        return this.employerEmail;
    }

    public Practitioner employerEmail(String employerEmail) {
        this.setEmployerEmail(employerEmail);
        return this;
    }

    public void setEmployerEmail(String employerEmail) {
        this.employerEmail = employerEmail;
    }

    public String getDateOfEmployment() {
        return this.dateOfEmployment;
    }

    public Practitioner dateOfEmployment(String dateOfEmployment) {
        this.setDateOfEmployment(dateOfEmployment);
        return this;
    }

    public void setDateOfEmployment(String dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public AreaOfEmployment getAreaOfEmployment() {
        return this.areaOfEmployment;
    }

    public Practitioner areaOfEmployment(AreaOfEmployment areaOfEmployment) {
        this.setAreaOfEmployment(areaOfEmployment);
        return this;
    }

    public void setAreaOfEmployment(AreaOfEmployment areaOfEmployment) {
        this.areaOfEmployment = areaOfEmployment;
    }

    public EmploymentStatus getEmploymentStatus() {
        return this.employmentStatus;
    }

    public Practitioner employmentStatus(EmploymentStatus employmentStatus) {
        this.setEmploymentStatus(employmentStatus);
        return this;
    }

    public void setEmploymentStatus(EmploymentStatus employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public TypeOfInstitution getTypeOfInstitution() {
        return this.typeOfInstitution;
    }

    public Practitioner typeOfInstitution(TypeOfInstitution typeOfInstitution) {
        this.setTypeOfInstitution(typeOfInstitution);
        return this;
    }

    public void setTypeOfInstitution(TypeOfInstitution typeOfInstitution) {
        this.typeOfInstitution = typeOfInstitution;
    }

    public Province getProvinceEmployed() {
        return this.provinceEmployed;
    }

    public Practitioner provinceEmployed(Province provinceEmployed) {
        this.setProvinceEmployed(provinceEmployed);
        return this;
    }

    public void setProvinceEmployed(Province provinceEmployed) {
        this.provinceEmployed = provinceEmployed;
    }

    public String getReasonForNonEmployment() {
        return this.reasonForNonEmployment;
    }

    public Practitioner reasonForNonEmployment(String reasonForNonEmployment) {
        this.setReasonForNonEmployment(reasonForNonEmployment);
        return this;
    }

    public void setReasonForNonEmployment(String reasonForNonEmployment) {
        this.reasonForNonEmployment = reasonForNonEmployment;
    }

    public String getDateOfApplication() {
        return this.dateOfApplication;
    }

    public Practitioner dateOfApplication(String dateOfApplication) {
        this.setDateOfApplication(dateOfApplication);
        return this;
    }

    public void setDateOfApplication(String dateOfApplication) {
        this.dateOfApplication = dateOfApplication;
    }

    public String getApplicationFee() {
        return this.applicationFee;
    }

    public Practitioner applicationFee(String applicationFee) {
        this.setApplicationFee(applicationFee);
        return this;
    }

    public void setApplicationFee(String applicationFee) {
        this.applicationFee = applicationFee;
    }

    public String getStatus() {
        return this.status;
    }

    public Practitioner status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReasonNotApproved() {
        return this.reasonNotApproved;
    }

    public Practitioner reasonNotApproved(String reasonNotApproved) {
        this.setReasonNotApproved(reasonNotApproved);
        return this;
    }

    public void setReasonNotApproved(String reasonNotApproved) {
        this.reasonNotApproved = reasonNotApproved;
    }

    public Set<Qualification> getQualifications() {
        return this.qualifications;
    }

    public void setQualifications(Set<Qualification> qualifications) {
        if (this.qualifications != null) {
            this.qualifications.forEach(i -> i.setPractitioner(null));
        }
        if (qualifications != null) {
            qualifications.forEach(i -> i.setPractitioner(this));
        }
        this.qualifications = qualifications;
    }

    public Practitioner qualifications(Set<Qualification> qualifications) {
        this.setQualifications(qualifications);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Practitioner)) {
            return false;
        }
        return getId() != null && getId().equals(((Practitioner) o).getId());
    }

    @Override
    public int hashCode() {
        // see
        // https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
	@Override
	public String toString() {
		return "Practitioner{" + "id=" + getId() + ", practitionerType='" + getPractitionerType() + "'"
				+ ", registrationNumber='" + getRegistrationNumber() + "'" + ", title='" + getTitle() + "'"
				+ ", surname='" + getSurname() + "'" + ", forenames='" + getForenames() + "'" + ", previousSurname='"
				+ getPreviousSurname() + "'" + ", dob='" + getDob() + "'" + ", gender='" + getGender() + "'"
				+ ", placeOfBirthTown='" + getPlaceOfBirthTown() + "'" + ", placeOfBirthCountry='"
				+ getPlaceOfBirthCountry() + "'" + ", nationality='" + getNationality() + "'" + ", nationalId='"
				+ getNationalId() + "'" + ", maritalStatus='" + getMaritalStatus() + "'" + ", residentialAddress1='"
				+ getResidentialAddress1() + "'" + ", residentialAddress2='" + getResidentialAddress2() + "'"
				+ ", residentialAddress3='" + getResidentialAddress3() + "'" + ", homePhone='" + getHomePhone() + "'"
				+ ", workPhone='" + getWorkPhone() + "'" + ", cellPhone='" + getCellPhone() + "'" + ", emailAddress='"
				+ getEmailAddress() + "'" + ", nameOfPlaceOfEmployment='" + getNameOfPlaceOfEmployment() + "'"
				+ ", employerAddress='" + getEmployerAddress() + "'" + ", employerEmail='" + getEmployerEmail() + "'"
				+ ", dateOfEmployment='" + getDateOfEmployment() + "'" + ", areaOfEmployment='" + getAreaOfEmployment()
				+ "'" + ", employmentStatus='" + getEmploymentStatus() + "'" + ", typeOfInstitution='"
				+ getTypeOfInstitution() + "'" + ", provinceEmployed='" + getProvinceEmployed() + "'"
				+ ", reasonForNonEmployment='" + getReasonForNonEmployment() + "'" + ", dateOfApplication='"
				+ getDateOfApplication() + "'" + ", applicationFee='" + getApplicationFee() + "'" + ", status='"
				+ getStatus() + "'" + ", reasonNotApproved='" + getReasonNotApproved() + "'" + "}";
	}
}
