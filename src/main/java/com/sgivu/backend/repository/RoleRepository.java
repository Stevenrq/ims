package com.sgivu.backend.repository;

import com.sgivu.backend.model.RoleEnum;
import com.sgivu.backend.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleEnum(RoleEnum roleEnum);

    boolean existsByRoleEnum(RoleEnum roleEnum);
}
