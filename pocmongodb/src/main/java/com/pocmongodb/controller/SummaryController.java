package com.pocmongodb.controller;

import com.pocmongodb.service.CustomReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/summary")
public class SummaryController {
    @Autowired
    CustomReportService service;

    @GetMapping("/findbydate")
    public ResponseEntity search(@RequestParam(value = "startPaymentDate") String startDate,
                                 @RequestParam(value = "endPaymentDate") String endDate) {
        return new ResponseEntity<>(service.findByDate(startDate, endDate), HttpStatus.OK);
    }
}
