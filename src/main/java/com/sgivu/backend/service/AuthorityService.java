package com.sgivu.backend.service;

import com.sgivu.backend.model.AuthorityEnum;
import com.sgivu.backend.model.entity.Authority;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AuthorityService {

    Authority save(Authority authority);

    Set<Authority> saveAll(Set<Authority> authorities);

    Optional<Authority> findById(Long id);

    boolean existsByAuthorityEnum(AuthorityEnum authorityEnum);

    Optional<Set<Authority>> findByAuthorityEnumIn(Set<AuthorityEnum> authorityEnums);

    List<Authority> findAll();
}
