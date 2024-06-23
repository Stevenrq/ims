package com.sgivu.backend.model.dto;

import com.sgivu.backend.model.entity.*;

import java.util.stream.Collectors;

/**
 * Clase para manejar la conversión de los diferentes DTOs de la aplicación.
 */
public class DtoConverter {

    public static PersonDto convertPersonToDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setIdentificationCard(person.getIdentificationCard());
        personDto.setName(person.getName());
        personDto.setLastName(person.getLastName());
        personDto.setAddress(person.getAddress());
        personDto.setPhone(person.getPhone());
        personDto.setEmail(person.getEmail());
        return personDto;
    }

    public static UserDto convertUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setPersonDto(convertPersonToDto(user));
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    public static CustomerDto convertCustomerToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setPersonDto(convertPersonToDto(customer));
        customerDto.setVehicleIds(customer.getVehicles().stream()
                .map(Vehicle::getId)
                .collect(Collectors.toSet())
        );
        return customerDto;
    }

    public static VehicleDto convertVehicleToDto(Vehicle vehicle) {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setId(vehicle.getId());
        vehicleDto.setTrademark(vehicle.getTrademark());
        vehicleDto.setModel(vehicle.getModel());
        vehicleDto.setModelLine(vehicle.getModelLine());
        vehicleDto.setCustomerId(vehicle.getCustomer().getId());
        return vehicleDto;
    }

    public static CarDto convertCarToDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setVehicleDto(convertVehicleToDto(car));
        carDto.setBodyType(car.getBodyType());
        carDto.setDoorsNumber(car.getDoorsNumber());
        return carDto;
    }

    public static MotorcycleDto convertMotorcycleToDto(Motorcycle motorcycle) {
        MotorcycleDto motorcycleDto = new MotorcycleDto();
        motorcycleDto.setVehicleDto(convertVehicleToDto(motorcycle));
        motorcycleDto.setMotorcycleType(motorcycle.getMotorcycleType());
        return motorcycleDto;
    }
}
