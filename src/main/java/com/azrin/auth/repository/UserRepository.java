package com.azrin.auth.repository;

import com.azrin.auth.model.User;
import com.azrin.auth.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public User findByEmail(String email){
        Query query=new Query();
        query.addCriteria(Criteria.where(Constant.EMAIL).is(email));
        return mongoTemplate.findOne(query, User.class);
    }

    public User saveUser(User user) throws Exception{
        User savedUser = mongoTemplate.save(user);
        return savedUser;
    }
}
