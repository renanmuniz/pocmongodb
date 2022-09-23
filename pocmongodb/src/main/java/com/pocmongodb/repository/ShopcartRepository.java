package com.pocmongodb.repository;

import com.pocmongodb.entity.Shopcart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopcartRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Shopcart insert(Shopcart shopcart) {
        return mongoTemplate.insert(shopcart, "shopcart");
    }

    public Shopcart find(String id) {
        Query query = new Query();
        if (null != id && !id.isEmpty()) {
            query.addCriteria(Criteria.where("id").is(id));
            return mongoTemplate.findOne(query, Shopcart.class);
        }
        return null;
    }

    public List<Shopcart> findAll() {
        return mongoTemplate.findAll(Shopcart.class);
    }

    public long update(String id, Shopcart shopcart) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Shopcart updateResult = mongoTemplate.findAndReplace(query, shopcart);
        if (null != updateResult) return 1L;
        return 0L;
    }

    public long delete(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Shopcart deleteResult = mongoTemplate.findAndRemove(query, Shopcart.class);
        if (null != deleteResult) return 1L;
        return 0L;
    }
}
