package com.medicallab.council.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Qualification.
 */
@Entity
@Table(name = "qualification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Qualification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "qualification_name")
    private String qualificationName;

    @Column(name = "name_of_institute")
    private String nameOfInstitute;

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;

    @Column(name = "awarded_by")
    private String awardedBy;

    @Column(name = "awarded_date")
    private LocalDate awardedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "qualifications" }, allowSetters = true)
    private Practitioner practitioner;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Qualification id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQualificationName() {
        return this.qualificationName;
    }

    public Qualification qualificationName(String qualificationName) {
        this.setQualificationName(qualificationName);
        return this;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getNameOfInstitute() {
        return this.nameOfInstitute;
    }

    public Qualification nameOfInstitute(String nameOfInstitute) {
        this.setNameOfInstitute(nameOfInstitute);
        return this;
    }

    public void setNameOfInstitute(String nameOfInstitute) {
        this.nameOfInstitute = nameOfInstitute;
    }

    public LocalDate getDateFrom() {
        return this.dateFrom;
    }

    public Qualification dateFrom(LocalDate dateFrom) {
        this.setDateFrom(dateFrom);
        return this;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return this.dateTo;
    }

    public Qualification dateTo(LocalDate dateTo) {
        this.setDateTo(dateTo);
        return this;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getAwardedBy() {
        return this.awardedBy;
    }

    public Qualification awardedBy(String awardedBy) {
        this.setAwardedBy(awardedBy);
        return this;
    }

    public void setAwardedBy(String awardedBy) {
        this.awardedBy = awardedBy;
    }

    public LocalDate getAwardedDate() {
        return this.awardedDate;
    }

    public Qualification awardedDate(LocalDate awardedDate) {
        this.setAwardedDate(awardedDate);
        return this;
    }

    public void setAwardedDate(LocalDate awardedDate) {
        this.awardedDate = awardedDate;
    }

    public Practitioner getPractitioner() {
        return this.practitioner;
    }

    public void setPractitioner(Practitioner practitioner) {
        this.practitioner = practitioner;
    }

    public Qualification practitioner(Practitioner practitioner) {
        this.setPractitioner(practitioner);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Qualification)) {
            return false;
        }
        return getId() != null && getId().equals(((Qualification) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Qualification{" +
            "id=" + getId() +
            ", qualificationName='" + getQualificationName() + "'" +
            ", nameOfInstitute='" + getNameOfInstitute() + "'" +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", awardedBy='" + getAwardedBy() + "'" +
            ", awardedDate='" + getAwardedDate() + "'" +
            "}";
    }
}
