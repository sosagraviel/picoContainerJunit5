package com.refactorizando.postman.example.repository;

import com.refactorizando.postman.example.domain.RentCar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;


public interface RentCarRepository extends MongoRepository<RentCar, String> {
    Optional<RentCar> findById(Long id);

    @Query(value = "{'id': ?0}")
    RentCar findByMethod(Long id);
}
