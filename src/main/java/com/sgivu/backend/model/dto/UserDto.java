package com.sgivu.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private PersonDto personDto;
    private String username;
}
