package com.harshal.firstSpringApp.Repository;

import com.harshal.firstSpringApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRespositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    public List<User> getUserForSA() {
        Query query = new Query();
//        Criteria criteria = new Criteria();
//        query.addCriteria(criteria.orOperator(Criteria.where("email").exists(true),
//                Criteria.where("SentimentAnalysis").is(true)));
        query.addCriteria(Criteria.where("email").exists(true).
                regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"));

        List<User> users = mongoTemplate.find(query, User.class);
        return users;

    }
}
