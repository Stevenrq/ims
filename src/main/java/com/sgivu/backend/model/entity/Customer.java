package com.sgivu.backend.model.entity;

import com.sgivu.backend.validation.annotation.ExistsByTaxIdentificationNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class Customer extends Person {

    /**
     * Número de identificación tributaria
     */
    @Column(name = "tax_identification_number", nullable = false, unique = true)
    @ExistsByTaxIdentificationNumber
    @NotNull
    private Long taxIdentificationNumber;

    @OneToMany(mappedBy = "customer")
    private Set<Vehicle> vehicles = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<Contract> contracts = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(taxIdentificationNumber, customer.taxIdentificationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), taxIdentificationNumber);
    }
}
