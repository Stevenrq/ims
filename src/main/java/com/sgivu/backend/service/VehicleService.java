package com.sgivu.backend.service;

import com.sgivu.backend.model.entity.Vehicle;

import java.util.Optional;

public interface VehicleService {

    Optional<Vehicle> findById(Long id);

    boolean existsByPlateNumber(String plateNumber);

    boolean existsByEngineNumber(String engineNumber);

    boolean existsBySerialNumber(String serialNumber);

    boolean existsByChassisNumber(String chassisNumber);
}
