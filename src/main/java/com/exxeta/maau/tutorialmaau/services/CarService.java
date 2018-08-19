package com.exxeta.maau.tutorialmaau.services;

import com.exxeta.maau.tutorialmaau.model.Car;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getAllCars();

    Car addCar (Car car, Long factoryId);

    void deleteCar (Long carId);

    //add car to factory
    Optional updateCar(Long carId);

}
