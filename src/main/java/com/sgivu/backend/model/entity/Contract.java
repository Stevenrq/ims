package com.sgivu.backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @NotNull
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    @NotNull
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    @NotNull
    private Vehicle vehicle;

    @Column(name = "purchase_price", nullable = false)
    @NotNull
    private Float purchasePrice;

    @Column(name = "sale_price", nullable = false)
    @NotNull
    private Float salePrice;

    @Column(name = "contract_date", nullable = false)
    @NotNull
    private LocalDateTime contractDate;

    @Column(name = "contract_type", nullable = false)
    @NotBlank
    private String contractType;

    @Column(name = "payment_conditions", nullable = false)
    @NotBlank
    private String paymentConditions;

    @Column(nullable = false)
    @NotBlank
    private String limitations;

    @Column(name = "payment_method")
    @NotBlank
    private String paymentMethod;

    @Column(nullable = false)
    @NotBlank
    private String observation;
}
