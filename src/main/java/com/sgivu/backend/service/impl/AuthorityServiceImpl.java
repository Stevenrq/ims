package com.sgivu.backend.service.impl;

import com.sgivu.backend.model.AuthorityEnum;
import com.sgivu.backend.model.entity.Authority;
import com.sgivu.backend.repository.AuthorityRepository;
import com.sgivu.backend.service.AuthorityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Transactional
    @Override
    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Transactional
    @Override
    public Set<Authority> saveAll(Set<Authority> authorities) {
        List<Authority> authorityList = authorityRepository.saveAll(authorities);
        return new HashSet<>(authorityList);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Authority> findById(Long id) {
        return authorityRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByAuthorityEnum(AuthorityEnum authorityEnum) {
        return authorityRepository.existsByAuthorityEnum(authorityEnum);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Set<Authority>> findByAuthorityEnumIn(Set<AuthorityEnum> authorityEnums) {
        return authorityRepository.findByAuthorityEnumIn(authorityEnums);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }
}
