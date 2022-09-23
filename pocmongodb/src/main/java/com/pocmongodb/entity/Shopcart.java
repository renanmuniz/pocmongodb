package com.pocmongodb.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pocmongodb.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "shopcart")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Shopcart {
    @Id
    private String id;
    private Customer customer;
    private Double totalAmount;
    private List<Order> orders;
    private LocalDateTime dateTime;
    private Status status;
}
