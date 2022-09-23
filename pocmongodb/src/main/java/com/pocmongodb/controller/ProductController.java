package com.pocmongodb.controller;

import com.pocmongodb.entity.Product;
import com.pocmongodb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService service;


    @PostMapping("/add")
    public ResponseEntity create(@RequestBody Product product) {
        return new ResponseEntity<>(service.insert(product), HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity search(@RequestParam(value = "name", required = false) String name) {
        return new ResponseEntity<>(service.find(name), HttpStatus.FOUND);
    }

    @PutMapping("/updateall")
    public ResponseEntity updateAll(@RequestParam(value = "id", required = true) String id,
                                    @RequestBody Product product) {
        if (service.updateAll(id, product) != 0L) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam(value = "id", required = true) String id) {
        if (service.delete(id) != 0L) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
