package com.refactorizando.postman.example.controller;

import com.refactorizando.postman.example.dto.CarDTO;
import com.refactorizando.postman.example.repository.CarRepository;
import com.refactorizando.postman.example.domain.Car;
import com.refactorizando.postman.example.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;
    private final CarService carService;


    @PostMapping
    public ResponseEntity<CarDTO> register(@RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(carService.createCar(carDTO));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping()
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable String id, @RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(carService.updateCar(id, carDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContributionById(@PathVariable String id) {
        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/specific")
    public ResponseEntity<CarDTO> getCars(@RequestParam("carBrand") String carBrand,@RequestParam("rentId") Long rentId) {
        return ResponseEntity.ok(carService.getSpecificCar(carBrand,rentId));
    }

}
