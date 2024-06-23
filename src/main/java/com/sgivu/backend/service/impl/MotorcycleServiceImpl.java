package com.sgivu.backend.service.impl;

import com.sgivu.backend.model.entity.Motorcycle;
import com.sgivu.backend.repository.MotorcycleRepository;
import com.sgivu.backend.service.MotorcycleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MotorcycleServiceImpl implements MotorcycleService {

    private final MotorcycleRepository motorcycleRepository;

    public MotorcycleServiceImpl(MotorcycleRepository motorcycleRepository) {
        this.motorcycleRepository = motorcycleRepository;
    }

    @Transactional
    @Override
    public Motorcycle save(Motorcycle motorcycle) {
        return motorcycleRepository.save(motorcycle);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Motorcycle> findById(Long id) {
        return motorcycleRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Motorcycle> findAll() {
        return motorcycleRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Motorcycle> update(Long id, Motorcycle motorcycle) {
        Optional<Motorcycle> optionalMotorcycle = motorcycleRepository.findById(id);
        if (optionalMotorcycle.isPresent()) {
            Motorcycle motorcycleDb = optionalMotorcycle.orElseThrow();
            motorcycleDb.setRepairHistories(motorcycle.getRepairHistories());
            motorcycleDb.setContracts(motorcycle.getContracts());
            motorcycleDb.setColor(motorcycle.getColor());
            motorcycleDb.setMileage(motorcycle.getMileage());
            motorcycleDb.setStatus(motorcycle.getStatus());
            return Optional.of(motorcycleRepository.save(motorcycleDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<Motorcycle> motorcycleOptional = motorcycleRepository.findById(id);
        motorcycleOptional.ifPresent(motorcycle -> {
            motorcycle.deleteCustomer(motorcycle.getCustomer());
            motorcycle.getRepairHistories().clear();
            motorcycle.getContracts().clear();
            motorcycleRepository.delete(motorcycle);
        });
    }
}
