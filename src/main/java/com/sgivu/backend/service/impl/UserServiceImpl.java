package com.sgivu.backend.service.impl;

import com.sgivu.backend.model.AuthorityEnum;
import com.sgivu.backend.model.RoleEnum;
import com.sgivu.backend.model.entity.User;
import com.sgivu.backend.repository.UserRepository;
import com.sgivu.backend.service.RoleService;
import com.sgivu.backend.service.UserService;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User save(User user) {
        Set<AuthorityEnum> userAuthorityEnums = Set.of(
                AuthorityEnum.BASIC_READ);

        Set<AuthorityEnum> adminAuthorityEnums = Set.of(
                AuthorityEnum.VIEW_DASHBOARD,
                AuthorityEnum.MANAGE_USER,
                AuthorityEnum.MANAGE_VEHICLE,
                AuthorityEnum.MANAGE_ROLES,
                AuthorityEnum.MANAGE_AUTHORITIES);
        try {
            if (user.hasAnyRole()) {
                roleService.addRoleToUser(user, RoleEnum.USER, userAuthorityEnums);
            } else if (user.isAdmin()) {
                roleService.addRoleToUser(user, RoleEnum.ADMIN, adminAuthorityEnums);
            }
        } catch (RoleNotFoundException e) {
            logger.error("Error al agregar el rol al usuario. {}", e.getMessage());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByPassword(String password) {
        return userRepository.existsByPassword(password);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<User> update(Long id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User userDb = userOptional.orElseThrow();
            userDb.setName(user.getName());
            userDb.setLastName(user.getLastName());
            userDb.setAddress(user.getAddress());
            userDb.setPhone(user.getPhone());
            userDb.setEmail(user.getEmail());
            userDb.setUsername(user.getUsername());
            userDb.setPassword(passwordEncoder.encode(user.getPassword()));
            return Optional.of(userRepository.save(userDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(user -> {
            user.getRoles().clear();
            userRepository.delete(user);
        });
    }
}
