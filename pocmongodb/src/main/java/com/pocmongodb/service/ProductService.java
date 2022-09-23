package com.pocmongodb.service;

import com.pocmongodb.entity.Product;
import com.pocmongodb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;

    public Product insert(Product product) {
        return repository.insert(product);
    }

    public List<Product> find(String name) {
        return repository.find(name);
    }

    public long updateAll(String id, Product product) {
        return repository.update(id, product);
    }

    public long delete(String id) {
        return repository.delete(id);
    }
}
