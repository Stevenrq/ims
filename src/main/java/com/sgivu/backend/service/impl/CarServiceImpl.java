package com.sgivu.backend.service.impl;

import com.sgivu.backend.model.entity.Car;
import com.sgivu.backend.model.entity.Customer;
import com.sgivu.backend.repository.CarRepository;
import com.sgivu.backend.service.CarService;
import com.sgivu.backend.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CustomerService customerService;

    public CarServiceImpl(CarRepository carRepository, CustomerService customerService) {
        this.carRepository = carRepository;
        this.customerService = customerService;
    }

    @Transactional
    @Override
    public Car save(Car car) {
        Optional<Customer> customerOptional = customerService.findById(car.getCustomer().getId());
        if (customerOptional.isPresent()) {
            Customer customerDb = customerOptional.orElseThrow();
            customerDb.getVehicles().add(car);
            car.setCustomer(customerDb);
        } else {
            throw new EntityNotFoundException("Cliente no encontrado");
        }
        return carRepository.save(car);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Car> update(Long id, Car car) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            Car carDb = carOptional.orElseThrow();
            carDb.setRepairHistories(car.getRepairHistories());
            carDb.setContracts(car.getContracts());
            carDb.setColor(car.getColor());
            carDb.setMileage(car.getMileage());
            carDb.setStatus(car.getStatus());
            return Optional.of(carRepository.save(carDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        carOptional.ifPresent(car -> {
            car.deleteCustomer(car.getCustomer());
            car.getRepairHistories().clear();
            car.getContracts().clear();
            carRepository.delete(car);
        });
    }
}
