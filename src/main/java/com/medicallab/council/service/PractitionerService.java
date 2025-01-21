package com.medicallab.council.service;

import com.medicallab.council.domain.Practitioner;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.medicallab.council.domain.Practitioner}.
 */
public interface PractitionerService {
    /**
     * Save a practitioner.
     *
     * @param practitioner the entity to save.
     * @return the persisted entity.
     */
    Practitioner save(Practitioner practitioner);

    /**
     * Updates a practitioner.
     *
     * @param practitioner the entity to update.
     * @return the persisted entity.
     */
    Practitioner update(Practitioner practitioner);

    /**
     * Partially updates a practitioner.
     *
     * @param practitioner the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Practitioner> partialUpdate(Practitioner practitioner);

    /**
     * Get all the practitioners.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Practitioner> findAll(Pageable pageable);

    /**
     * Get the "id" practitioner.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Practitioner> findOne(Long id);

    /**
     * Delete the "id" practitioner.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
