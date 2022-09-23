package com.pocmongodb.controller;

import com.pocmongodb.entity.Shopcart;
import com.pocmongodb.service.ShopcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopcart")
public class ShopcartController {
    @Autowired
    ShopcartService service;


    @PostMapping("/add")
    public ResponseEntity create(@RequestBody Shopcart shopcart) {
        return new ResponseEntity<>(service.insert(shopcart), HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity search(@RequestParam(value = "id", required = false) String id) {
        if(null != id && !id.isEmpty()) return new ResponseEntity<>(service.find(id), HttpStatus.FOUND);
        return new ResponseEntity<>(service.findAll(), HttpStatus.FOUND);
    }

    @PutMapping("/updateall")
    public ResponseEntity updateAll(@RequestParam(value = "id", required = true) String id,
                                    @RequestBody Shopcart shopcart) {
        if (service.updateAll(id, shopcart) != 0L) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam(value = "id", required = true) String id) {
        if (service.delete(id) != 0L) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
