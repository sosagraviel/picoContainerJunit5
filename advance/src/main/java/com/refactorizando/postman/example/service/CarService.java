package com.refactorizando.postman.example.service;

import com.refactorizando.postman.example.domain.Car;
import com.refactorizando.postman.example.dto.CarDTO;
import com.refactorizando.postman.example.dto.RentCarDTO;
import com.refactorizando.postman.example.exeptions.MissingCarException;
import com.refactorizando.postman.example.exeptions.MissingRentCarException;
import com.refactorizando.postman.example.mapper.RentCarsMapper;
import com.refactorizando.postman.example.repository.CarRepository;
import com.refactorizando.postman.example.repository.RentCarRepository;
import com.refactorizando.postman.example.domain.RentCar;
import com.refactorizando.postman.example.exeptions.ErrorConstants;
import com.refactorizando.postman.example.mapper.CarsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final RentCarRepository rentCarRepository;
    private final CarRepository carRepository;

    public CarDTO createCar(CarDTO carDTO) {
        Car car = CarsMapper.INSTANCE.dtoToCar(carDTO);
        if (carDTO.getRentCarId() != null) {
            if (rentCarRepository.findById(carDTO.getRentCarId()).isPresent()) {
                car.setRentCar(getRentCarById(String.valueOf(carDTO.getId())));
                carRepository.save(car);
            } else {
                throw new MissingRentCarException(ErrorConstants.MISSING_RENT_CAR);
            }
        }
        carRepository.save(car);
        return CarsMapper.INSTANCE.carsToDTO(car);
    }

    private RentCar getRentCarById(String rent_card_Id) {
        Optional<RentCar> optionalRentCard = rentCarRepository.findById(rent_card_Id);
        if (optionalRentCard.isPresent()) {
            return optionalRentCard.get();
        } else {
            throw new MissingRentCarException(ErrorConstants.MISSING_RENT_CAR);
        }
    }

    public Car updateCar(String carId, CarDTO carDTO) {
        Optional<Car> carOpt = carRepository.findById(Long.valueOf(carId));
        Optional<RentCar> rentCarOpt = rentCarRepository.findById(carDTO.getRentCarId());
        if (carOpt.isPresent()) {
            Car car = carOpt.get();
            if (rentCarOpt.isPresent()) {
                if (rentCarOpt.get().getId() != null) {
                    car.setRentCar(new RentCar());
                    car.setRentCar(rentCarOpt.get());
                    carRepository.save(car);
                    return car;
                } else {
                    throw new MissingRentCarException(ErrorConstants.MISSING_RENT_CAR);
                }
            }
            throw new MissingRentCarException(ErrorConstants.MISSING_RENT_CAR);
        } else {
            throw new MissingCarException(ErrorConstants.MISSING_CAR);
        }
    }

    public void deleteCar(String id) {
        if (carRepository.existsById(Long.valueOf(id))) {
            carRepository.deleteById(Long.valueOf(id));
        } else {
            throw new MissingCarException(ErrorConstants.MISSING_CAR);
        }
    }

    public CarDTO getSpecificCar(String brand, Long id) {
        Optional<Car> car = carRepository.findByBrandAndRentCar_Id(brand, id);
        if (car.isPresent()) {
            return CarsMapper.INSTANCE.carsToDTO(car.get());
        } else {
            throw new MissingCarException(ErrorConstants.MISSING_CAR);
        }
    }
}
