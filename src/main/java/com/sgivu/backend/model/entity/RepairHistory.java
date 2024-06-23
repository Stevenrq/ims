package com.sgivu.backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "repair_histories")
@Entity
public class RepairHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Column(name = "repair_date", nullable = false)
    @NotNull
    private LocalDateTime repairDate;

    @Column(nullable = false)
    @NotBlank
    private String description;

    @Column(name = "repair_cost", nullable = false)
    @NotNull
    private Float repairCost;
}
