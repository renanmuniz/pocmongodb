package com.pocmongodb.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customer")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Customer {
    @Id
    private String id;
    private String name;
    private Integer age;
}
