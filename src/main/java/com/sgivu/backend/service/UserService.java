package com.sgivu.backend.service;

import java.util.List;
import java.util.Optional;

import com.sgivu.backend.model.entity.User;

public interface UserService {

    User save(User user);

    Optional<User> findById(Long id);

    boolean existsByUsername(String username);

    boolean existsByPassword(String password);

    List<User> findAll();

    Optional<User> update(Long id, User user);

    void deleteById(Long id);
}
