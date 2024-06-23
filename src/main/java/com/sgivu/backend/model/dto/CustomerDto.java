package com.sgivu.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CustomerDto {
    private PersonDto personDto;
    private Set<Long> vehicleIds = new HashSet<>();
}
