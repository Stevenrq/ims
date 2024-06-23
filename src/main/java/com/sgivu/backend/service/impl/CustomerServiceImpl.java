package com.sgivu.backend.service.impl;

import com.sgivu.backend.model.entity.Customer;
import com.sgivu.backend.repository.CustomerRepository;
import com.sgivu.backend.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByTaxIdentificationNumber(Long taxIdentificationNumber) {
        return customerRepository.existsByTaxIdentificationNumber(taxIdentificationNumber);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Customer> update(Long id, Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customerDb = customerOptional.orElseThrow();
            customerDb.setName(customer.getName());
            customerDb.setLastName(customer.getLastName());
            customerDb.setAddress(customer.getAddress());
            customerDb.setPhone(customer.getPhone());
            customerDb.setEmail(customer.getEmail());
            customerDb.setVehicles(customer.getVehicles());
            customer.setContracts(customer.getContracts());
            return Optional.of(customerRepository.save(customerDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        customerOptional.ifPresent(customer -> {
            customer.getVehicles().clear();
            customer.getContracts().clear();
            customerRepository.delete(customer);
        });
    }
}
