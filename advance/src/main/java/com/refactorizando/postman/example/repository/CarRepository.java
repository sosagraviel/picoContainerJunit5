package com.refactorizando.postman.example.repository;

import com.refactorizando.postman.example.domain.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface CarRepository extends MongoRepository<Car, Long> {
    List<Car> findByRentCar_Id(Long id);

    Optional<Car> findByBrandAndRentCar_Id(String carBrand, Long id);
}
