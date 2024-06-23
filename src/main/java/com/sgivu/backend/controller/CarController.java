package com.sgivu.backend.controller;

import com.sgivu.backend.model.dto.CarDto;
import com.sgivu.backend.model.dto.DtoConverter;
import com.sgivu.backend.model.entity.Car;
import com.sgivu.backend.service.CarService;
import com.sgivu.backend.util.ValidationUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.validation(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoConverter.convertCarToDto(carService.save(car)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> get(@PathVariable Long id) {
        Optional<Car> carOptional = carService.findById(id);
        if (carOptional.isPresent()) {
            return ResponseEntity.ok(DtoConverter.convertCarToDto(carOptional.orElseThrow()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getAll() {
        List<CarDto> cars = carService.findAll().stream()
                .map(DtoConverter::convertCarToDto).toList();
        return ResponseEntity.ok(cars);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.validation(bindingResult);
        }
        Optional<Car> carOptional = carService.update(id, car);
        if (carOptional.isPresent()) {
            return ResponseEntity.ok(DtoConverter.convertCarToDto(carOptional.orElseThrow()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Car> carOptional = carService.findById(id);
        if (carOptional.isPresent()) {
            carService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
