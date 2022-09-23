package com.pocmongodb.repository;

import com.pocmongodb.entity.Shopcart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class CustomReportRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Shopcart> findByDate(LocalDateTime startDate, LocalDateTime endDate) {
        MatchOperation dateFilter = match(Criteria.where("dateTime").gte(startDate).lte(endDate));
        SortOperation sortOperation = sort(Sort.by(Sort.Direction.ASC, "dateTime"));

        Aggregation aggregation = newAggregation(
                dateFilter,
                sortOperation
        );

        return mongoTemplate.aggregate(aggregation, "shopcart", Shopcart.class).getMappedResults();
    }
}
