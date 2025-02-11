package com.medicallab.council.service.impl;

import com.medicallab.council.domain.Practitioner;
import com.medicallab.council.domain.enumeration.PractitionerType;
import com.medicallab.council.repository.PractitionerRepository;
import com.medicallab.council.service.AttachmentService;
import com.medicallab.council.service.PractitionerService;
import com.medicallab.council.service.QualificationService;
import com.medicallab.council.service.dto.PractitionerCountDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing
 * {@link com.medicallab.council.domain.Practitioner}.
 */
@Service
@Transactional
public class PractitionerServiceImpl implements PractitionerService {

    private static final Logger LOG = LoggerFactory.getLogger(PractitionerServiceImpl.class);

    private final PractitionerRepository practitionerRepository;
    private final QualificationService qualificationService;
    private final AttachmentService attachmentService;

    public PractitionerServiceImpl(
        PractitionerRepository practitionerRepository,
        QualificationService qualificationService,
        AttachmentService attachmentService
    ) {
        this.practitionerRepository = practitionerRepository;
        this.qualificationService = qualificationService;
        this.attachmentService = attachmentService;
    }

    @Override
    public Practitioner save(Practitioner practitioner) {
        LOG.debug("Request to save Practitioner : {}", practitioner);
        Practitioner saved = practitionerRepository.save(practitioner);
        if (saved != null && practitioner.getQualifications() != null) {
            qualificationService.saveAll(practitioner.getQualifications(), saved);
        }
        LOG.debug("Request to List the saved Practitioner : {}", saved);
        return saved;
    }

    @Override
    public Practitioner update(Practitioner practitioner) {
        LOG.debug("Request to update Practitioner : {}", practitioner);
        Practitioner saved = practitionerRepository.save(practitioner);
        /*
         * if(saved !=null && practitioner.getAttachment()!=null) {
         * LOG.debug("Request to Save Multipart File  : {}",
         * practitioner.getAttachment());
         * attachmentService.saveAttachment(practitioner.getAttachment()); }
         */
        return saved;
    }

    @Override
    public Optional<Practitioner> partialUpdate(Practitioner practitioner) {
        LOG.debug("Request to partially update Practitioner : {}", practitioner);

        return practitionerRepository
            .findById(practitioner.getId())
            .map(existingPractitioner -> {
                if (practitioner.getPractitionerType() != null) {
                    existingPractitioner.setPractitionerType(practitioner.getPractitionerType());
                }
                if (practitioner.getRegistrationNumber() != null) {
                    existingPractitioner.setRegistrationNumber(practitioner.getRegistrationNumber());
                }
                if (practitioner.getTitle() != null) {
                    existingPractitioner.setTitle(practitioner.getTitle());
                }
                if (practitioner.getSurname() != null) {
                    existingPractitioner.setSurname(practitioner.getSurname());
                }
                if (practitioner.getForenames() != null) {
                    existingPractitioner.setForenames(practitioner.getForenames());
                }
                if (practitioner.getPreviousSurname() != null) {
                    existingPractitioner.setPreviousSurname(practitioner.getPreviousSurname());
                }
                if (practitioner.getDob() != null) {
                    existingPractitioner.setDob(practitioner.getDob());
                }
                if (practitioner.getGender() != null) {
                    existingPractitioner.setGender(practitioner.getGender());
                }
                if (practitioner.getPlaceOfBirthTown() != null) {
                    existingPractitioner.setPlaceOfBirthTown(practitioner.getPlaceOfBirthTown());
                }
                if (practitioner.getPlaceOfBirthCountry() != null) {
                    existingPractitioner.setPlaceOfBirthCountry(practitioner.getPlaceOfBirthCountry());
                }
                if (practitioner.getNationality() != null) {
                    existingPractitioner.setNationality(practitioner.getNationality());
                }
                if (practitioner.getNationalId() != null) {
                    existingPractitioner.setNationalId(practitioner.getNationalId());
                }
                if (practitioner.getMaritalStatus() != null) {
                    existingPractitioner.setMaritalStatus(practitioner.getMaritalStatus());
                }
                if (practitioner.getResidentialAddress1() != null) {
                    existingPractitioner.setResidentialAddress1(practitioner.getResidentialAddress1());
                }
                if (practitioner.getResidentialAddress2() != null) {
                    existingPractitioner.setResidentialAddress2(practitioner.getResidentialAddress2());
                }
                if (practitioner.getResidentialAddress3() != null) {
                    existingPractitioner.setResidentialAddress3(practitioner.getResidentialAddress3());
                }
                if (practitioner.getHomePhone() != null) {
                    existingPractitioner.setHomePhone(practitioner.getHomePhone());
                }
                if (practitioner.getWorkPhone() != null) {
                    existingPractitioner.setWorkPhone(practitioner.getWorkPhone());
                }
                if (practitioner.getCellPhone() != null) {
                    existingPractitioner.setCellPhone(practitioner.getCellPhone());
                }
                if (practitioner.getEmailAddress() != null) {
                    existingPractitioner.setEmailAddress(practitioner.getEmailAddress());
                }
                if (practitioner.getNameOfPlaceOfEmployment() != null) {
                    existingPractitioner.setNameOfPlaceOfEmployment(practitioner.getNameOfPlaceOfEmployment());
                }
                if (practitioner.getEmployerAddress() != null) {
                    existingPractitioner.setEmployerAddress(practitioner.getEmployerAddress());
                }
                if (practitioner.getEmployerEmail() != null) {
                    existingPractitioner.setEmployerEmail(practitioner.getEmployerEmail());
                }
                if (practitioner.getDateOfEmployment() != null) {
                    existingPractitioner.setDateOfEmployment(practitioner.getDateOfEmployment());
                }
                if (practitioner.getAreaOfEmployment() != null) {
                    existingPractitioner.setAreaOfEmployment(practitioner.getAreaOfEmployment());
                }
                if (practitioner.getEmploymentStatus() != null) {
                    existingPractitioner.setEmploymentStatus(practitioner.getEmploymentStatus());
                }
                if (practitioner.getTypeOfInstitution() != null) {
                    existingPractitioner.setTypeOfInstitution(practitioner.getTypeOfInstitution());
                }
                if (practitioner.getProvinceEmployed() != null) {
                    existingPractitioner.setProvinceEmployed(practitioner.getProvinceEmployed());
                }
                if (practitioner.getReasonForNonEmployment() != null) {
                    existingPractitioner.setReasonForNonEmployment(practitioner.getReasonForNonEmployment());
                }
                if (practitioner.getDateOfApplication() != null) {
                    existingPractitioner.setDateOfApplication(practitioner.getDateOfApplication());
                }
                if (practitioner.getApplicationFee() != null) {
                    existingPractitioner.setApplicationFee(practitioner.getApplicationFee());
                }
                if (practitioner.getStatus() != null) {
                    existingPractitioner.setStatus(practitioner.getStatus());
                }
                if (practitioner.getReasonNotApproved() != null) {
                    existingPractitioner.setReasonNotApproved(practitioner.getReasonNotApproved());
                }

                return existingPractitioner;
            })
            .map(practitionerRepository::save);
    }

    @Override
    public List<PractitionerCountDTO> getPractitionerCounts() {
        List<PractitionerCountDTO> counts = new ArrayList<>();

        for (PractitionerType type : PractitionerType.values()) {
            Long count = practitionerRepository.countByPractitionerType(type);
            counts.add(new PractitionerCountDTO(type.name(), count));
        }

        return counts;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Practitioner> findAll(Pageable pageable) {
        LOG.debug("Request to get all Practitioners");
        return practitionerRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Practitioner> findOne(Long id) {
        LOG.debug("Request to get Practitioner : {}", id);
        return practitionerRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Practitioner : {}", id);
        practitionerRepository.deleteById(id);
    }
}
