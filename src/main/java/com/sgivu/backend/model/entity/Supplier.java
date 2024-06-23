package com.sgivu.backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier extends Person {

    /**
     * Número de identificación tributaria
     */
    @Column(name = "tin", nullable = false, unique = true)
    @NotNull
    private Long tin;

    @NotBlank
    @Column(name = "supplier_type", nullable = false)
    private String supplierType;

    @OneToMany(mappedBy = "supplier")
    private Set<Contract> contracts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(tin, supplier.tin) && Objects.equals(supplierType, supplier.supplierType) && Objects.equals(contracts, supplier.contracts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tin, supplierType, contracts);
    }
}
