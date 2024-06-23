package com.sgivu.backend.service;

import com.sgivu.backend.model.entity.Motorcycle;

import java.util.List;
import java.util.Optional;

public interface MotorcycleService {

    Motorcycle save(Motorcycle motorcycle);

    Optional<Motorcycle> findById(Long id);

    List<Motorcycle> findAll();

    Optional<Motorcycle> update(Long id, Motorcycle motorcycle);

    void deleteById(Long id);
}
