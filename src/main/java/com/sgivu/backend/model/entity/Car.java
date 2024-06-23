package com.sgivu.backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends Vehicle {

    @Column(name = "body_type", nullable = false)
    @NotBlank
    private String bodyType;

    @Column(name = "doors_number", nullable = false)
    @NotNull
    private Integer doorsNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return Objects.equals(bodyType, car.bodyType) && Objects.equals(doorsNumber, car.doorsNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bodyType, doorsNumber);
    }
}
