package com.pocmongodb.service;

import com.pocmongodb.entity.Shopcart;
import com.pocmongodb.repository.ShopcartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShopcartService {
    @Autowired
    ShopcartRepository repository;

    public Shopcart insert(Shopcart shopcart) {
        shopcart.setDateTime(LocalDateTime.now());
        return repository.insert(shopcart);
    }

    public Shopcart find(String id) {
        return repository.find(id);
    }

    public List<Shopcart> findAll() {
        return repository.findAll();
    }

    public long updateAll(String id, Shopcart shopcart) {
        shopcart.setDateTime(LocalDateTime.now());
        return repository.update(id, shopcart);
    }

    public long delete(String id) {
        return repository.delete(id);
    }
}
