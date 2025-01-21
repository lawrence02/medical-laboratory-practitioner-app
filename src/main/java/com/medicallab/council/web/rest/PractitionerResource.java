package com.medicallab.council.web.rest;

import com.medicallab.council.domain.Practitioner;
import com.medicallab.council.repository.PractitionerRepository;
import com.medicallab.council.service.PractitionerService;
import com.medicallab.council.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.medicallab.council.domain.Practitioner}.
 */
@RestController
@RequestMapping("/api/practitioners")
public class PractitionerResource {

    private static final Logger LOG = LoggerFactory.getLogger(PractitionerResource.class);

    private static final String ENTITY_NAME = "practitioner";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PractitionerService practitionerService;

    private final PractitionerRepository practitionerRepository;

    public PractitionerResource(PractitionerService practitionerService, PractitionerRepository practitionerRepository) {
        this.practitionerService = practitionerService;
        this.practitionerRepository = practitionerRepository;
    }

    /**
     * {@code POST  /practitioners} : Create a new practitioner.
     *
     * @param practitioner the practitioner to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new practitioner, or with status {@code 400 (Bad Request)} if the practitioner has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Practitioner> createPractitioner(@Valid @RequestBody Practitioner practitioner) throws URISyntaxException {
        LOG.debug("REST request to save Practitioner : {}", practitioner);
        if (practitioner.getId() != null) {
            throw new BadRequestAlertException("A new practitioner cannot already have an ID", ENTITY_NAME, "idexists");
        }
        practitioner = practitionerService.save(practitioner);
        return ResponseEntity.created(new URI("/api/practitioners/" + practitioner.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, practitioner.getId().toString()))
            .body(practitioner);
    }

    /**
     * {@code PUT  /practitioners/:id} : Updates an existing practitioner.
     *
     * @param id the id of the practitioner to save.
     * @param practitioner the practitioner to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated practitioner,
     * or with status {@code 400 (Bad Request)} if the practitioner is not valid,
     * or with status {@code 500 (Internal Server Error)} if the practitioner couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Practitioner> updatePractitioner(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Practitioner practitioner
    ) throws URISyntaxException {
        LOG.debug("REST request to update Practitioner : {}, {}", id, practitioner);
        if (practitioner.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, practitioner.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!practitionerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        practitioner = practitionerService.update(practitioner);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, practitioner.getId().toString()))
            .body(practitioner);
    }

    /**
     * {@code PATCH  /practitioners/:id} : Partial updates given fields of an existing practitioner, field will ignore if it is null
     *
     * @param id the id of the practitioner to save.
     * @param practitioner the practitioner to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated practitioner,
     * or with status {@code 400 (Bad Request)} if the practitioner is not valid,
     * or with status {@code 404 (Not Found)} if the practitioner is not found,
     * or with status {@code 500 (Internal Server Error)} if the practitioner couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Practitioner> partialUpdatePractitioner(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Practitioner practitioner
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Practitioner partially : {}, {}", id, practitioner);
        if (practitioner.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, practitioner.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!practitionerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Practitioner> result = practitionerService.partialUpdate(practitioner);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, practitioner.getId().toString())
        );
    }

    /**
     * {@code GET  /practitioners} : get all the practitioners.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of practitioners in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Practitioner>> getAllPractitioners(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of Practitioners");
        Page<Practitioner> page = practitionerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /practitioners/:id} : get the "id" practitioner.
     *
     * @param id the id of the practitioner to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the practitioner, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Practitioner> getPractitioner(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Practitioner : {}", id);
        Optional<Practitioner> practitioner = practitionerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(practitioner);
    }

    /**
     * {@code DELETE  /practitioners/:id} : delete the "id" practitioner.
     *
     * @param id the id of the practitioner to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePractitioner(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Practitioner : {}", id);
        practitionerService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
