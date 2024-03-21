package com.refactorizando.postman.example.service;

import com.refactorizando.postman.example.domain.Car;
import com.refactorizando.postman.example.domain.RentCar;
import com.refactorizando.postman.example.dto.CarDTO;
import com.refactorizando.postman.example.dto.RentCarDTO;
import com.refactorizando.postman.example.dto.UserDTO;
import com.refactorizando.postman.example.exeptions.ErrorConstants;
import com.refactorizando.postman.example.exeptions.MissingRentCarException;
import com.refactorizando.postman.example.exeptions.MissingUserException;
import com.refactorizando.postman.example.mapper.CarsMapper;
import com.refactorizando.postman.example.mapper.RentCarsMapper;
import com.refactorizando.postman.example.mapper.UserMapper;
import com.refactorizando.postman.example.repository.CarRepository;
import com.refactorizando.postman.example.repository.RentCarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentCarService {

    private final RentCarRepository rentCarRepository;
    private final RentCarsMapper rentCarsMapper;
    private final CarRepository carRepository;

    public RentCarDTO createRentCar(RentCarDTO rentCarDTO) {
        RentCar toRentCard;
        rentCarRepository.save(toRentCard = rentCarsMapper.dtoToRentCar(rentCarDTO));
        return rentCarsMapper.rentCarToDTO(toRentCard);
    }

    public RentCarDTO getRentCarById(String rent_card_Id) {
        Optional<RentCar> optionalRentCard = rentCarRepository.findById(Long.valueOf(rent_card_Id));
        if (optionalRentCard.isPresent()) {
            return RentCarsMapper.INSTANCE.rentCarToDTO(optionalRentCard.get());
        } else {
            throw new MissingRentCarException(ErrorConstants.MISSING_RENT_CAR);
        }
    }

    public List<CarDTO> getAllCarFromRentCard(String id) {
        List<Car> cars = carRepository.findByRentCar_Id(Long.valueOf(id));
        if (!cars.isEmpty()) {
            return CarsMapper.INSTANCE.toCarsDTO(cars);
        } else {
            throw new MissingRentCarException(ErrorConstants.MISSING_RENT_CAR);
        }
    }

    public List<RentCarDTO> getRentCars() {
        if (!rentCarRepository.findAll().isEmpty()) {
            return RentCarsMapper.INSTANCE.toCarsDTO(rentCarRepository.findAll());
        } else {
            throw new MissingRentCarException(ErrorConstants.MISSING_RENT_CAR);
        }
    }
}
