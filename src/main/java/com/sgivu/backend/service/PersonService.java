package com.sgivu.backend.service;

public interface PersonService {

    boolean existsByIdentificationCard(Long identificationCard);

    boolean existsByEmail(String email);

    boolean existsByPhone(Long phone);
}
