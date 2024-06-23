package com.sgivu.backend.repository;

import com.sgivu.backend.model.AuthorityEnum;
import com.sgivu.backend.model.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Optional<Set<Authority>> findByAuthorityEnumIn(Set<AuthorityEnum> authorityEnums);

    boolean existsByAuthorityEnum(AuthorityEnum authorityEnum);
}
