package com.pocmongodb.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PurchaseDto {
    private String name;
    private Double total;
    private LocalDateTime dateTime;
    private List<ProductDto> productDto;
}
