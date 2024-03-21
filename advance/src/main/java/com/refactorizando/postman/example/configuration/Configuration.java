package com.refactorizando.postman.example.configuration;

import com.refactorizando.postman.example.repository.RentCarRepository;
import com.refactorizando.postman.example.domain.Car;
import com.refactorizando.postman.example.domain.RentCar;
import com.refactorizando.postman.example.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor

public class Configuration implements CommandLineRunner {

    private final CarRepository carRepository;
    private final RentCarRepository rentCarRepository;

    @Override
    public void run(String... args) {
        List<Car> cars = new ArrayList<>();
        List<RentCar> rentCars = new ArrayList<>();
        if (rentCarRepository.count() == 0) {
            rentCars.add(new RentCar(1L, "default name"));
            rentCarRepository.saveAll(rentCars);
        }
        if (carRepository.count() == 0) {
            cars.add(new Car(1L, "ferrary", "black", new RentCar(1L, "default name")));
            carRepository.saveAll(cars);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer problemObjectMapperModules() {
        return jacksonObjectMapperBuilder ->
                jacksonObjectMapperBuilder.modules(
                        new ProblemModule(), new ConstraintViolationProblemModule());
    }
}