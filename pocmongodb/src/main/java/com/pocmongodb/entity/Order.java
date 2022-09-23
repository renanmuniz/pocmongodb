package com.pocmongodb.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Order {
    @Id
    private String id;
    private Product product;
    private Integer quantity;
    private Double totalPrice;
    private LocalDateTime dateTime;
}
