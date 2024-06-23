package com.sgivu.backend.service.impl;

import com.sgivu.backend.model.AuthorityEnum;
import com.sgivu.backend.model.RoleEnum;
import com.sgivu.backend.model.entity.Authority;
import com.sgivu.backend.model.entity.Role;
import com.sgivu.backend.model.entity.User;
import com.sgivu.backend.repository.RoleRepository;
import com.sgivu.backend.service.AuthorityService;
import com.sgivu.backend.service.RoleService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final AuthorityService authorityService;

    public RoleServiceImpl(RoleRepository roleRepository, AuthorityService authorityService) {
        this.roleRepository = roleRepository;
        this.authorityService = authorityService;
    }

    @Transactional
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    @Override
    public Set<Role> saveAll(Set<Role> roles) {
        List<Role> rolesList = roleRepository.saveAll(roles);
        return new HashSet<>(rolesList);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByRoleEnum(RoleEnum roleEnum) {
        return roleRepository.existsByRoleEnum(roleEnum);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Transactional
    @Override
    public void addRoleToUser(User user, RoleEnum roleEnum, Set<AuthorityEnum> authorityEnums)
            throws RoleNotFoundException {

        Role role = roleRepository.findByRoleEnum(roleEnum)
                .orElseThrow(() -> new RoleNotFoundException("No se encontró el rol en la base de datos"));

        Set<Authority> authorities = authorityService.findByAuthorityEnumIn(authorityEnums)
                .orElseThrow(() -> new EntityNotFoundException("No se encontraron las autoridades en la base de datos"));

        role.setAuthorities(authorities);
        user.getRoles().add(role);
        roleRepository.save(role);
    }
}
