package com.pocmongodb.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String description;
    private Integer quantity;
    private Double total;
}
