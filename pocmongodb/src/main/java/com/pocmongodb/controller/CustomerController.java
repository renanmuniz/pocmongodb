package com.pocmongodb.controller;

import com.pocmongodb.entity.Customer;
import com.pocmongodb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService service;


    @PostMapping("/add")
    public ResponseEntity create(@RequestBody Customer customer) {
        return new ResponseEntity<>(service.insert(customer),HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity search(@RequestParam(value = "name", required = false) String name) {
        return new ResponseEntity<>(service.find(name),HttpStatus.FOUND);
    }

    @PutMapping("/updatename")
    public ResponseEntity update(@RequestParam(value = "id", required = true) String id,
                                 @RequestParam(value = "name", required = true) String name) {
        if(service.updateName(id, name) != 0L) return new ResponseEntity<>(HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateall")
    public ResponseEntity updateAll(@RequestParam(value = "id", required = true) String id,
                                 @RequestBody Customer customer) {
        if(service.updateAll(id, customer)!=0L) return new ResponseEntity<>(HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam(value = "id", required = true) String id) {
        if(service.delete(id)!=0L) return new ResponseEntity<>(HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
