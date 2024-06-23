package com.sgivu.backend.service;

import com.sgivu.backend.model.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

    boolean existsByTaxIdentificationNumber(Long taxIdentificationNumber);

    List<Customer> findAll();

    Optional<Customer> update(Long id, Customer customer);

    void deleteById(Long id);
}
