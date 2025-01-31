package com.medicallab.council.service.impl;

import com.medicallab.council.domain.Practitioner;
import com.medicallab.council.domain.Qualification;
import com.medicallab.council.repository.QualificationRepository;
import com.medicallab.council.service.QualificationService;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing
 * {@link com.medicallab.council.domain.Qualification}.
 */
@Service
@Transactional
public class QualificationServiceImpl implements QualificationService {

    private static final Logger LOG = LoggerFactory.getLogger(QualificationServiceImpl.class);

    private final QualificationRepository qualificationRepository;

    public QualificationServiceImpl(QualificationRepository qualificationRepository) {
        this.qualificationRepository = qualificationRepository;
    }

    @Override
    public Qualification save(Qualification qualification) {
        LOG.debug("Request to save Qualification : {}", qualification);
        return qualificationRepository.save(qualification);
    }

    @Override
    public Qualification update(Qualification qualification) {
        LOG.debug("Request to update Qualification : {}", qualification);
        return qualificationRepository.save(qualification);
    }

    @Override
    public Optional<Qualification> partialUpdate(Qualification qualification) {
        LOG.debug("Request to partially update Qualification : {}", qualification);

        return qualificationRepository
            .findById(qualification.getId())
            .map(existingQualification -> {
                if (qualification.getQualificationName() != null) {
                    existingQualification.setQualificationName(qualification.getQualificationName());
                }
                if (qualification.getNameOfInstitute() != null) {
                    existingQualification.setNameOfInstitute(qualification.getNameOfInstitute());
                }
                if (qualification.getDateFrom() != null) {
                    existingQualification.setDateFrom(qualification.getDateFrom());
                }
                if (qualification.getDateTo() != null) {
                    existingQualification.setDateTo(qualification.getDateTo());
                }
                if (qualification.getAwardedBy() != null) {
                    existingQualification.setAwardedBy(qualification.getAwardedBy());
                }
                if (qualification.getAwardedDate() != null) {
                    existingQualification.setAwardedDate(qualification.getAwardedDate());
                }

                return existingQualification;
            })
            .map(qualificationRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Qualification> findAll(Pageable pageable) {
        LOG.debug("Request to get all Qualifications");
        return qualificationRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Qualification> findOne(Long id) {
        LOG.debug("Request to get Qualification : {}", id);
        return qualificationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Qualification : {}", id);
        qualificationRepository.deleteById(id);
    }

    @Override
    public void saveAll(Set<Qualification> qualifications, Practitioner practitioner) {
        qualifications.forEach(q -> q.setPractitioner(practitioner));
        qualificationRepository.saveAll(qualifications);
    }
}
