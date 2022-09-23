package com.pocmongodb.service;

import com.pocmongodb.entity.Order;
import com.pocmongodb.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository repository;

    public Order insert(Order order) {
        order.setDateTime(LocalDateTime.now());
        return repository.insert(order);
    }

    public Order find(String id) {
        return repository.find(id);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public long updateAll(String id, Order order) {
        order.setDateTime(LocalDateTime.now());
        return repository.update(id, order);
    }

    public long delete(String id) {
        return repository.delete(id);
    }
}
