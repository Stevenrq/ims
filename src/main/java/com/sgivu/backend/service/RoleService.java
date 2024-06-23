package com.sgivu.backend.service;

import com.sgivu.backend.model.AuthorityEnum;
import com.sgivu.backend.model.RoleEnum;
import com.sgivu.backend.model.entity.Role;
import com.sgivu.backend.model.entity.User;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleService {

    Role save(Role role);

    Set<Role> saveAll(Set<Role> roles);

    Optional<Role> findById(Long id);

    boolean existsByRoleEnum(RoleEnum roleEnum);

    List<Role> findAll();

    /**
     * Agrega un rol con sus respectivas autoridades al usuario.
     *
     * @param user           al que se le agrega el rol
     * @param roleEnum       nombre del rol
     * @param authorityEnums autoridades que se le asignan al rol
     * @throws RoleNotFoundException si el rol no existe
     */
    void addRoleToUser(User user, RoleEnum roleEnum, Set<AuthorityEnum> authorityEnums) throws RoleNotFoundException;
}
