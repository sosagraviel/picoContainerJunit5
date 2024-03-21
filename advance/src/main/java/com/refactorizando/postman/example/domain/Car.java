package com.refactorizando.postman.example.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car {
    @Id
    private Long id;
    private String brand;
    private String color;
    private RentCar rentCar;
}
