package com.medicallab.council.service;

import com.medicallab.council.domain.Practitioner;
import com.medicallab.council.domain.Qualification;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.medicallab.council.domain.Qualification}.
 */
public interface QualificationService {
    /**
     * Save a qualification.
     *
     * @param qualification the entity to save.
     * @return the persisted entity.
     */
    Qualification save(Qualification qualification);

    /**
     * Updates a qualification.
     *
     * @param qualification the entity to update.
     * @return the persisted entity.
     */
    Qualification update(Qualification qualification);

    /**
     * Partially updates a qualification.
     *
     * @param qualification the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Qualification> partialUpdate(Qualification qualification);

    /**
     * Get all the qualifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Qualification> findAll(Pageable pageable);

    /**
     * Get the "id" qualification.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Qualification> findOne(Long id);

    /**
     * Delete the "id" qualification.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void saveAll(Set<Qualification> qualifications, Practitioner saved);
}
