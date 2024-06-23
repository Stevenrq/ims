package com.sgivu.backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "motorcycles")
public class Motorcycle extends Vehicle {

    @Column(name = "motorcycle_type", nullable = false)
    @NotBlank
    private String motorcycleType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Motorcycle that = (Motorcycle) o;
        return Objects.equals(motorcycleType, that.motorcycleType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), motorcycleType);
    }
}
