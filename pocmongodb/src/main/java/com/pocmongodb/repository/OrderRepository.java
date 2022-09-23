package com.pocmongodb.repository;

import com.pocmongodb.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Order insert(Order order) {
        return mongoTemplate.insert(order, "order");
    }

    public Order find(String id) {
        Query query = new Query();
        if (null != id && !id.isEmpty()) {
            query.addCriteria(Criteria.where("id").is(id));
            return mongoTemplate.findOne(query, Order.class);
        }
        return null;
    }

    public List<Order> findAll() {
        return mongoTemplate.findAll(Order.class);
    }

    public long update(String id, Order order) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Order updateResult = mongoTemplate.findAndReplace(query, order);
        if (null != updateResult) return 1L;
        return 0L;
    }

    public long delete(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Order deleteResult = mongoTemplate.findAndRemove(query, Order.class);
        if (null != deleteResult) return 1L;
        return 0L;
    }
}
