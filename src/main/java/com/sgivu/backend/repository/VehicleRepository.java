package com.sgivu.backend.repository;

import com.sgivu.backend.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    boolean existsByPlateNumber(String plateNumber);

    boolean existsByEngineNumber(String engineNumber);

    boolean existsBySerialNumber(String serialNumber);

    boolean existsByChassisNumber(String chassisNumber);
}
