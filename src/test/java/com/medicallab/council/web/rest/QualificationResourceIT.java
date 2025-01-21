package com.medicallab.council.web.rest;

import static com.medicallab.council.domain.QualificationAsserts.*;
import static com.medicallab.council.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medicallab.council.IntegrationTest;
import com.medicallab.council.domain.Qualification;
import com.medicallab.council.repository.QualificationRepository;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link QualificationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QualificationResourceIT {

    private static final String DEFAULT_QUALIFICATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_QUALIFICATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_OF_INSTITUTE = "AAAAAAAAAA";
    private static final String UPDATED_NAME_OF_INSTITUTE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_AWARDED_BY = "AAAAAAAAAA";
    private static final String UPDATED_AWARDED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_AWARDED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AWARDED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/qualifications";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQualificationMockMvc;

    private Qualification qualification;

    private Qualification insertedQualification;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Qualification createEntity() {
        return new Qualification()
            .qualificationName(DEFAULT_QUALIFICATION_NAME)
            .nameOfInstitute(DEFAULT_NAME_OF_INSTITUTE)
            .dateFrom(DEFAULT_DATE_FROM)
            .dateTo(DEFAULT_DATE_TO)
            .awardedBy(DEFAULT_AWARDED_BY)
            .awardedDate(DEFAULT_AWARDED_DATE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Qualification createUpdatedEntity() {
        return new Qualification()
            .qualificationName(UPDATED_QUALIFICATION_NAME)
            .nameOfInstitute(UPDATED_NAME_OF_INSTITUTE)
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .awardedBy(UPDATED_AWARDED_BY)
            .awardedDate(UPDATED_AWARDED_DATE);
    }

    @BeforeEach
    public void initTest() {
        qualification = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedQualification != null) {
            qualificationRepository.delete(insertedQualification);
            insertedQualification = null;
        }
    }

    @Test
    @Transactional
    void createQualification() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Qualification
        var returnedQualification = om.readValue(
            restQualificationMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualification)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Qualification.class
        );

        // Validate the Qualification in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertQualificationUpdatableFieldsEquals(returnedQualification, getPersistedQualification(returnedQualification));

        insertedQualification = returnedQualification;
    }

    @Test
    @Transactional
    void createQualificationWithExistingId() throws Exception {
        // Create the Qualification with an existing ID
        qualification.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQualificationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualification)))
            .andExpect(status().isBadRequest());

        // Validate the Qualification in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQualifications() throws Exception {
        // Initialize the database
        insertedQualification = qualificationRepository.saveAndFlush(qualification);

        // Get all the qualificationList
        restQualificationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qualification.getId().intValue())))
            .andExpect(jsonPath("$.[*].qualificationName").value(hasItem(DEFAULT_QUALIFICATION_NAME)))
            .andExpect(jsonPath("$.[*].nameOfInstitute").value(hasItem(DEFAULT_NAME_OF_INSTITUTE)))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].awardedBy").value(hasItem(DEFAULT_AWARDED_BY)))
            .andExpect(jsonPath("$.[*].awardedDate").value(hasItem(DEFAULT_AWARDED_DATE.toString())));
    }

    @Test
    @Transactional
    void getQualification() throws Exception {
        // Initialize the database
        insertedQualification = qualificationRepository.saveAndFlush(qualification);

        // Get the qualification
        restQualificationMockMvc
            .perform(get(ENTITY_API_URL_ID, qualification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(qualification.getId().intValue()))
            .andExpect(jsonPath("$.qualificationName").value(DEFAULT_QUALIFICATION_NAME))
            .andExpect(jsonPath("$.nameOfInstitute").value(DEFAULT_NAME_OF_INSTITUTE))
            .andExpect(jsonPath("$.dateFrom").value(DEFAULT_DATE_FROM.toString()))
            .andExpect(jsonPath("$.dateTo").value(DEFAULT_DATE_TO.toString()))
            .andExpect(jsonPath("$.awardedBy").value(DEFAULT_AWARDED_BY))
            .andExpect(jsonPath("$.awardedDate").value(DEFAULT_AWARDED_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingQualification() throws Exception {
        // Get the qualification
        restQualificationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQualification() throws Exception {
        // Initialize the database
        insertedQualification = qualificationRepository.saveAndFlush(qualification);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualification
        Qualification updatedQualification = qualificationRepository.findById(qualification.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedQualification are not directly saved in db
        em.detach(updatedQualification);
        updatedQualification
            .qualificationName(UPDATED_QUALIFICATION_NAME)
            .nameOfInstitute(UPDATED_NAME_OF_INSTITUTE)
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .awardedBy(UPDATED_AWARDED_BY)
            .awardedDate(UPDATED_AWARDED_DATE);

        restQualificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedQualification.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedQualification))
            )
            .andExpect(status().isOk());

        // Validate the Qualification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQualificationToMatchAllProperties(updatedQualification);
    }

    @Test
    @Transactional
    void putNonExistingQualification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualification.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, qualification.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualification))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQualification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualification.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualification))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQualification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualification.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualificationMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualification)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Qualification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQualificationWithPatch() throws Exception {
        // Initialize the database
        insertedQualification = qualificationRepository.saveAndFlush(qualification);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualification using partial update
        Qualification partialUpdatedQualification = new Qualification();
        partialUpdatedQualification.setId(qualification.getId());

        partialUpdatedQualification.nameOfInstitute(UPDATED_NAME_OF_INSTITUTE).dateFrom(UPDATED_DATE_FROM).dateTo(UPDATED_DATE_TO);

        restQualificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualification.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualification))
            )
            .andExpect(status().isOk());

        // Validate the Qualification in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualificationUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedQualification, qualification),
            getPersistedQualification(qualification)
        );
    }

    @Test
    @Transactional
    void fullUpdateQualificationWithPatch() throws Exception {
        // Initialize the database
        insertedQualification = qualificationRepository.saveAndFlush(qualification);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualification using partial update
        Qualification partialUpdatedQualification = new Qualification();
        partialUpdatedQualification.setId(qualification.getId());

        partialUpdatedQualification
            .qualificationName(UPDATED_QUALIFICATION_NAME)
            .nameOfInstitute(UPDATED_NAME_OF_INSTITUTE)
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .awardedBy(UPDATED_AWARDED_BY)
            .awardedDate(UPDATED_AWARDED_DATE);

        restQualificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualification.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualification))
            )
            .andExpect(status().isOk());

        // Validate the Qualification in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualificationUpdatableFieldsEquals(partialUpdatedQualification, getPersistedQualification(partialUpdatedQualification));
    }

    @Test
    @Transactional
    void patchNonExistingQualification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualification.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, qualification.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualification))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQualification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualification.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualification))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQualification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualification.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualificationMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(qualification)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Qualification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQualification() throws Exception {
        // Initialize the database
        insertedQualification = qualificationRepository.saveAndFlush(qualification);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the qualification
        restQualificationMockMvc
            .perform(delete(ENTITY_API_URL_ID, qualification.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return qualificationRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Qualification getPersistedQualification(Qualification qualification) {
        return qualificationRepository.findById(qualification.getId()).orElseThrow();
    }

    protected void assertPersistedQualificationToMatchAllProperties(Qualification expectedQualification) {
        assertQualificationAllPropertiesEquals(expectedQualification, getPersistedQualification(expectedQualification));
    }

    protected void assertPersistedQualificationToMatchUpdatableProperties(Qualification expectedQualification) {
        assertQualificationAllUpdatablePropertiesEquals(expectedQualification, getPersistedQualification(expectedQualification));
    }
}
