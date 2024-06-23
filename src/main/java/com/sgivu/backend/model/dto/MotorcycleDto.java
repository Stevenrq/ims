package com.sgivu.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotorcycleDto {
    private VehicleDto vehicleDto;
    private String motorcycleType;
}
