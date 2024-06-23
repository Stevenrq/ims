package com.sgivu.backend.controller;

import com.sgivu.backend.model.dto.DtoConverter;
import com.sgivu.backend.model.dto.MotorcycleDto;
import com.sgivu.backend.model.entity.Motorcycle;
import com.sgivu.backend.service.MotorcycleService;
import com.sgivu.backend.util.ValidationUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/motorcycles")
public class MotorcycleController {

    private final MotorcycleService motorcycleService;

    public MotorcycleController(MotorcycleService motorcycleService) {
        this.motorcycleService = motorcycleService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Motorcycle motorcycle, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.validation(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoConverter.convertMotorcycleToDto(motorcycleService.save(motorcycle)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotorcycleDto> get(@PathVariable Long id) {
        Optional<Motorcycle> motorcycleOptional = motorcycleService.findById(id);
        if (motorcycleOptional.isPresent()) {
            return ResponseEntity.ok(DtoConverter.convertMotorcycleToDto(motorcycleOptional.orElseThrow()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<MotorcycleDto>> getAll() {
        List<MotorcycleDto> motorcycles = motorcycleService.findAll().stream()
                .map(DtoConverter::convertMotorcycleToDto).toList();
        return ResponseEntity.ok(motorcycles);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Motorcycle motorcycle, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.validation(bindingResult);
        }
        Optional<Motorcycle> motorcycleOptional = motorcycleService.update(id, motorcycle);
        if (motorcycleOptional.isPresent()) {
            return ResponseEntity.ok(DtoConverter.convertMotorcycleToDto(motorcycleOptional.orElseThrow()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Motorcycle> motorcycleOptional = motorcycleService.findById(id);
        if (motorcycleOptional.isPresent()) {
            motorcycleService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
