package com.pocmongodb.service;

import com.pocmongodb.entity.Customer;
import com.pocmongodb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public Customer insert(Customer customer) {
        return repository.insert(customer);
    }

    public List<Customer> find(String name) {
        return repository.find(name);
    }

    public long updateName(String id, String name) {
        return repository.updateName(id, name);
    }

    public long updateAll(String id, Customer customer) {
        return repository.updateAll(id, customer);
    }

    public long delete(String id) {
        return repository.delete(id);
    }
}
