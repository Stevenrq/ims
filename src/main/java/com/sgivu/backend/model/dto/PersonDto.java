package com.sgivu.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {
    private Long id;
    private Long identificationCard;
    private String name;
    private String lastName;
    private String address;
    private Long phone;
    private String email;
}
