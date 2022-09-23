package com.pocmongodb.service;

import com.pocmongodb.dto.ProductDto;
import com.pocmongodb.dto.PurchaseDto;
import com.pocmongodb.entity.Order;
import com.pocmongodb.entity.Shopcart;
import com.pocmongodb.repository.CustomReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomReportService {

    @Autowired
    CustomReportRepository repository;

    public List<PurchaseDto> findByDate(String startDate, String endDate) {
        LocalDateTime startDateTime = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE).atTime(0,0,0);
        LocalDateTime endDateTime = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE).atTime(23,59,59);

        List<Shopcart> aggregatedResults = repository.findByDate(startDateTime,endDateTime);

        List<PurchaseDto> list = new ArrayList<>();
        for (Shopcart shopcart : aggregatedResults) {
            PurchaseDto dto = new PurchaseDto();
            dto.setName(shopcart.getCustomer().getName());
            dto.setDateTime(shopcart.getDateTime());
            dto.setTotal(shopcart.getTotalAmount());

            List<ProductDto> products = new ArrayList<>();
            for (Order order : shopcart.getOrders()) {
                ProductDto productDto = new ProductDto();
                productDto.setName(order.getProduct().getName());
                productDto.setDescription(order.getProduct().getDescription());
                productDto.setQuantity(order.getQuantity());
                productDto.setTotal(order.getTotalPrice());
                products.add(productDto);
            }
            dto.setProductDto(products);
            list.add(dto);
        }

        return list;
    }

}
