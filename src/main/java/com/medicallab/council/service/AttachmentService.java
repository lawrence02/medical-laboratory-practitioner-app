package com.medicallab.council.service;

import com.medicallab.council.domain.Attachment;
import com.medicallab.council.domain.Practitioner;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Interface for managing {@link com.medicallab.council.domain.Attchment}.
 */
public interface AttachmentService {
    /**
     * Save a attchment.
     *
     * @param set the entity to save.
     * @return the persisted entity.
     */
    void saveAttachment(MultipartFile set);

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
    Optional<MultipartFile> findOne(Long id);

    /**
     * Delete the "id" practitioner.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
