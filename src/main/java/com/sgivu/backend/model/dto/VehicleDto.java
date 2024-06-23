package com.sgivu.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleDto {
    private Long id;
    private String trademark;
    private String model;
    private String modelLine;
    private Long customerId;
}
