package com.pocmongodb.controller;

import com.pocmongodb.entity.Order;
import com.pocmongodb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService service;


    @PostMapping("/add")
    public ResponseEntity create(@RequestBody Order order) {
        return new ResponseEntity<>(service.insert(order), HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity search(@RequestParam(value = "id", required = false) String id) {
        if(null != id && !id.isEmpty()) return new ResponseEntity<>(service.find(id), HttpStatus.FOUND);
        return new ResponseEntity<>(service.findAll(), HttpStatus.FOUND);
    }

    @PutMapping("/updateall")
    public ResponseEntity updateAll(@RequestParam(value = "id", required = true) String id,
                                    @RequestBody Order order) {
        if (service.updateAll(id, order) != 0L) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam(value = "id", required = true) String id) {
        if (service.delete(id) != 0L) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
