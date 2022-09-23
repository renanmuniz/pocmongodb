package com.pocmongodb.repository;

import com.mongodb.client.result.UpdateResult;
import com.pocmongodb.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Pattern;

@Repository
public class CustomerRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Customer insert(Customer customer) {
        return mongoTemplate.insert(customer, "customer");
    }

    public List<Customer> find(String name) {
        Query query = new Query();
        if(null != name && !name.isEmpty()) {
            query.addCriteria(Criteria.where("name").regex(Pattern.compile(name)));
        } else {
            query.addCriteria(Criteria.where("name").exists(true));
        }
        return mongoTemplate.find(query, Customer.class);
    }

    public long updateName(String id, String name) {
        if(null == id || id.isEmpty()) return 0L;
        if(null == name) return 0L;

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Update update = new Update().set("name", name);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Customer.class);
        return updateResult.getModifiedCount();
    }

    public long updateAll(String id, Customer customer) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Customer updateResult = mongoTemplate.findAndReplace(query, customer);
        if(null != updateResult) return 1L;
        return 0L;
    }

    public long delete(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Customer deleteResult = mongoTemplate.findAndRemove(query, Customer.class);
        if(null != deleteResult) return 1L;
        return 0L;
    }
}
