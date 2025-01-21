package com.medicallab.council.web.rest;

import com.medicallab.council.domain.Qualification;
import com.medicallab.council.repository.QualificationRepository;
import com.medicallab.council.service.QualificationService;
import com.medicallab.council.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.medicallab.council.domain.Qualification}.
 */
@RestController
@RequestMapping("/api/qualifications")
public class QualificationResource {

    private static final Logger LOG = LoggerFactory.getLogger(QualificationResource.class);

    private static final String ENTITY_NAME = "qualification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QualificationService qualificationService;

    private final QualificationRepository qualificationRepository;

    public QualificationResource(QualificationService qualificationService, QualificationRepository qualificationRepository) {
        this.qualificationService = qualificationService;
        this.qualificationRepository = qualificationRepository;
    }

    /**
     * {@code POST  /qualifications} : Create a new qualification.
     *
     * @param qualification the qualification to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qualification, or with status {@code 400 (Bad Request)} if the qualification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Qualification> createQualification(@RequestBody Qualification qualification) throws URISyntaxException {
        LOG.debug("REST request to save Qualification : {}", qualification);
        if (qualification.getId() != null) {
            throw new BadRequestAlertException("A new qualification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        qualification = qualificationService.save(qualification);
        return ResponseEntity.created(new URI("/api/qualifications/" + qualification.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, qualification.getId().toString()))
            .body(qualification);
    }

    /**
     * {@code PUT  /qualifications/:id} : Updates an existing qualification.
     *
     * @param id the id of the qualification to save.
     * @param qualification the qualification to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualification,
     * or with status {@code 400 (Bad Request)} if the qualification is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qualification couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Qualification> updateQualification(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Qualification qualification
    ) throws URISyntaxException {
        LOG.debug("REST request to update Qualification : {}, {}", id, qualification);
        if (qualification.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualification.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualificationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        qualification = qualificationService.update(qualification);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualification.getId().toString()))
            .body(qualification);
    }

    /**
     * {@code PATCH  /qualifications/:id} : Partial updates given fields of an existing qualification, field will ignore if it is null
     *
     * @param id the id of the qualification to save.
     * @param qualification the qualification to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualification,
     * or with status {@code 400 (Bad Request)} if the qualification is not valid,
     * or with status {@code 404 (Not Found)} if the qualification is not found,
     * or with status {@code 500 (Internal Server Error)} if the qualification couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Qualification> partialUpdateQualification(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Qualification qualification
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Qualification partially : {}, {}", id, qualification);
        if (qualification.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualification.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualificationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Qualification> result = qualificationService.partialUpdate(qualification);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qualification.getId().toString())
        );
    }

    /**
     * {@code GET  /qualifications} : get all the qualifications.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qualifications in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Qualification>> getAllQualifications(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of Qualifications");
        Page<Qualification> page = qualificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qualifications/:id} : get the "id" qualification.
     *
     * @param id the id of the qualification to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qualification, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Qualification> getQualification(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Qualification : {}", id);
        Optional<Qualification> qualification = qualificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qualification);
    }

    /**
     * {@code DELETE  /qualifications/:id} : delete the "id" qualification.
     *
     * @param id the id of the qualification to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualification(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Qualification : {}", id);
        qualificationService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
