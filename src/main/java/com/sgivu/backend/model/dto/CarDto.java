package com.sgivu.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDto {
    private VehicleDto vehicleDto;
    private String bodyType;
    private Integer doorsNumber;
}
