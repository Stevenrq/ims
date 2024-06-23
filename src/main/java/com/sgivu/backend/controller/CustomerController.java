package com.sgivu.backend.controller;

import com.sgivu.backend.model.dto.CustomerDto;
import com.sgivu.backend.model.dto.DtoConverter;
import com.sgivu.backend.model.entity.Customer;
import com.sgivu.backend.service.CustomerService;
import com.sgivu.backend.util.ValidationUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.validation(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoConverter.convertCustomerToDto(customerService.save(customer)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (customerOptional.isPresent()) {
            return ResponseEntity.ok(DtoConverter.convertCustomerToDto(customerOptional.orElseThrow()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        List<CustomerDto> customers = customerService.findAll().stream()
                .map(DtoConverter::convertCustomerToDto).toList();
        return ResponseEntity.ok(customers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.validation(bindingResult);
        }
        Optional<Customer> customerOptional = customerService.update(id, customer);
        if (customerOptional.isPresent()) {
            return ResponseEntity.ok(DtoConverter.convertCustomerToDto(customerOptional.orElseThrow()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (customerOptional.isPresent()) {
            customerService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
