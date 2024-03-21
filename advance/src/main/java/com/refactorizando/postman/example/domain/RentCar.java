package com.refactorizando.postman.example.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RentCar {
    @Id
    private Long id;
    private String name;
}
