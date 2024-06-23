package com.sgivu.backend.service.impl;

import com.sgivu.backend.repository.PersonRepository;
import com.sgivu.backend.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByIdentificationCard(Long identificationCard) {
        return personRepository.existsByIdentificationCard(identificationCard);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByEmail(String email) {
        return personRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByPhone(Long phone) {
        return personRepository.existsByPhone(phone);
    }
}
