package com.refactorizando.postman.example.mapper;
import com.refactorizando.postman.example.domain.Car;
import com.refactorizando.postman.example.dto.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarsMapper {
    CarsMapper INSTANCE = Mappers.getMapper(CarsMapper.class);
    @Mapping(source = "rentCar.id",target = "rentCarId")
    CarDTO carsToDTO(Car car);
    Car dtoToCar(CarDTO carDTO);
    List<CarDTO> toCarsDTO(List<Car> cars);
}
