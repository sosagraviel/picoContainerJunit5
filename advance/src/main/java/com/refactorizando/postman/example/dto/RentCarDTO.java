package com.refactorizando.postman.example.dto;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RentCarDTO {
    private Long id;
    private String name;
}
