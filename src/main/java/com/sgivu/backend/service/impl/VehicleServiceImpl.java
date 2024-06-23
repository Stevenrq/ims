package com.sgivu.backend.service.impl;

import com.sgivu.backend.model.entity.Vehicle;
import com.sgivu.backend.repository.VehicleRepository;
import com.sgivu.backend.service.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return vehicleRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByPlateNumber(String plateNumber) {
        return vehicleRepository.existsByPlateNumber(plateNumber);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByEngineNumber(String engineNumber) {
        return vehicleRepository.existsByEngineNumber(engineNumber);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsBySerialNumber(String serialNumber) {
        return vehicleRepository.existsBySerialNumber(serialNumber);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByChassisNumber(String chassisNumber) {
        return vehicleRepository.existsByChassisNumber(chassisNumber);
    }
}
