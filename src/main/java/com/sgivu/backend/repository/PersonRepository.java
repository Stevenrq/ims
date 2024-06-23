package com.sgivu.backend.repository;

import com.sgivu.backend.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    boolean existsByIdentificationCard(Long identificationCard);

    boolean existsByEmail(String email);

    boolean existsByPhone(Long phone);

}
