package com.sgivu.backend.service;

import com.sgivu.backend.model.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    Car save(Car car);

    Optional<Car> findById(Long id);

    List<Car> findAll();

    Optional<Car> update(Long id, Car car);

    void deleteById(Long id);
}
