package com.medicallab.council.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Payment.
 */
@Entity
@Table(name = "payment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "date_paid")
    private LocalDate datePaid;

    @Column(name = "subscription_period")
    private String subscriptionPeriod;

    @Column(name = "amount")
    private String amount;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Payment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public Payment registrationNumber(String registrationNumber) {
        this.setRegistrationNumber(registrationNumber);
        return this;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public LocalDate getDatePaid() {
        return this.datePaid;
    }

    public Payment datePaid(LocalDate datePaid) {
        this.setDatePaid(datePaid);
        return this;
    }

    public void setDatePaid(LocalDate datePaid) {
        this.datePaid = datePaid;
    }

    public String getSubscriptionPeriod() {
        return this.subscriptionPeriod;
    }

    public Payment subscriptionPeriod(String subscriptionPeriod) {
        this.setSubscriptionPeriod(subscriptionPeriod);
        return this;
    }

    public void setSubscriptionPeriod(String subscriptionPeriod) {
        this.subscriptionPeriod = subscriptionPeriod;
    }

    public String getAmount() {
        return this.amount;
    }

    public Payment amount(String amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Payment)) {
            return false;
        }
        return getId() != null && getId().equals(((Payment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Payment{" +
            "id=" + getId() +
            ", registrationNumber='" + getRegistrationNumber() + "'" +
            ", datePaid='" + getDatePaid() + "'" +
            ", subscriptionPeriod='" + getSubscriptionPeriod() + "'" +
            ", amount='" + getAmount() + "'" +
            "}";
    }
}
