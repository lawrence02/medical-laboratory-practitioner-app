package com.medicallab.council.service.dto;

public class PractitionerCountDTO {

    private String practitionerType;
    private Long count;

    public PractitionerCountDTO(String practitionerType, Long count) {
        this.practitionerType = practitionerType;
        this.count = count;
    }

    // Getters and Setters
    public String getPractitionerType() {
        return practitionerType;
    }

    public void setPractitionerType(String practitionerType) {
        this.practitionerType = practitionerType;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
