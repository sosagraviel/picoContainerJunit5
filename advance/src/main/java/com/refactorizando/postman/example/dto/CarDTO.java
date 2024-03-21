package com.refactorizando.postman.example.dto;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CarDTO {
    private Long id;
    private String brand;
    private String color;
    private Long rentCarId;
}
