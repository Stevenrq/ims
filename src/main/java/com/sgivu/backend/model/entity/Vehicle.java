package com.sgivu.backend.model.entity;

import com.sgivu.backend.validation.annotation.ExistsByChassisNumber;
import com.sgivu.backend.validation.annotation.ExistsByEngineNumber;
import com.sgivu.backend.validation.annotation.ExistsByPlateNumber;
import com.sgivu.backend.validation.annotation.ExistsBySerialNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String trademark;

    @Column(nullable = false)
    private String model;

    @Column(name = "passenger_capacity", nullable = false)
    private String passengerCapacity;

    @Column(name = "model_line", nullable = false)
    private String modelLine;

    @Column(name = "plate_number", nullable = false, unique = true)
    @ExistsByPlateNumber
    private String plateNumber;

    @Column(name = "transmission_type", nullable = false)
    private String transmissionType;

    @ExistsByEngineNumber
    @Column(name = "engine_number", nullable = false, unique = true)
    private String engineNumber;

    @Column(name = "serial_number", nullable = false, unique = true)
    @ExistsBySerialNumber
    private String serialNumber;

    @Column(name = "chassis_number", nullable = false, unique = true)
    @ExistsByChassisNumber
    private String chassisNumber;

    @Column(nullable = false)
    private String color;

    @Column(name = "registered_in", nullable = false)
    private String registeredIn;

    @Column(name = "manufacturing_year", nullable = false)
    private Integer manufacturingYear;

    @Column(nullable = false)
    private Long mileage;

    @Column(nullable = false)
    private String status;

    @Column(name = "purchase_price", nullable = false)
    private Float purchasePrice;

    @Column(name = "sale_price", nullable = false)
    private Float salePrice;

    // @Column(nullable = false)
    private String image;

    @Column(name = "fuel_type", nullable = false)
    @NotBlank
    private String fuelType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "vehicle")
    private Set<RepairHistory> repairHistories;

    @OneToMany(mappedBy = "vehicle")
    private Set<Contract> contracts;

    /**
     * Eliminar el cliente asociado al vehículo.
     *
     * @param customer a ser eliminado
     */
    public void deleteCustomer(Customer customer) {
        customer.getVehicles().remove(this);
        this.setCustomer(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id, vehicle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
