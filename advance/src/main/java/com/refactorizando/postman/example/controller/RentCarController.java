package com.refactorizando.postman.example.controller;

import com.refactorizando.postman.example.dto.CarDTO;
import com.refactorizando.postman.example.dto.RentCarDTO;
import com.refactorizando.postman.example.mapper.RentCarsMapper;
import com.refactorizando.postman.example.repository.RentCarRepository;
import com.refactorizando.postman.example.service.RentCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent_cars")
public class RentCarController {

    private final RentCarService rentCarService;
    private final RentCarRepository rentCarRepository;

    @PostMapping
    public ResponseEntity<RentCarDTO> register(@RequestBody RentCarDTO rentCarDTO) {
        return ResponseEntity.ok(rentCarService.createRentCar(rentCarDTO));
    }

    @GetMapping()
    public ResponseEntity<List<RentCarDTO>> getRentCars() {
        return ResponseEntity.ok(rentCarService.getRentCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentCarDTO> getRentCarsById(@PathVariable String id) {
        return ResponseEntity.ok(rentCarService.getRentCarById(id));
    }

    @GetMapping("/cars/{rentCar_Id}")
    public ResponseEntity<List<CarDTO>> getCarsByRentCard(@PathVariable String rentCar_Id) {
        return ResponseEntity.ok(rentCarService.getAllCarFromRentCard(rentCar_Id));
    }
}
