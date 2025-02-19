package com.medicallab.council.repository;

import com.medicallab.council.domain.Practitioner;
import com.medicallab.council.domain.enumeration.PractitionerType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Practitioner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PractitionerRepository extends JpaRepository<Practitioner, Long> {
    Long countByPractitionerType(PractitionerType type);
}
