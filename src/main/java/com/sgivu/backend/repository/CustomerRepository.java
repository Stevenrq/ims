package com.sgivu.backend.repository;

import com.sgivu.backend.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByTaxIdentificationNumber(Long taxIdentificationNumber);
}
