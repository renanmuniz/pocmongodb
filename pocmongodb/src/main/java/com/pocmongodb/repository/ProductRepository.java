package com.pocmongodb.repository;

import com.pocmongodb.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Pattern;

@Repository
public class ProductRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Product insert(Product product) {
        return mongoTemplate.insert(product, "product");
    }

    public List<Product> find(String name) {
        Query query = new Query();
        if (null != name && !name.isEmpty()) {
            query.addCriteria(Criteria.where("name").regex(Pattern.compile(name)));
        } else {
            query.addCriteria(Criteria.where("name").exists(true));
        }
        return mongoTemplate.find(query, Product.class);
    }

    public long update(String id, Product product) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Product updateResult = mongoTemplate.findAndReplace(query, product);
        if (null != updateResult) return 1L;
        return 0L;
    }

    public long delete(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Product deleteResult = mongoTemplate.findAndRemove(query, Product.class);
        if (null != deleteResult) return 1L;
        return 0L;
    }
}
