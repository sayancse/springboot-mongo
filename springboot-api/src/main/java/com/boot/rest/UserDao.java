package com.boot.rest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.boot.rest.kafka.SpringMongoConfig;
import com.boot.rest.kafka.User;

@Service
public class UserDao {
	
	public User getUser() {
		
		ApplicationContext ctx = 
                new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	MongoOperations mongoOperation = 
                (MongoOperations) ctx.getBean("mongoTemplate");

	// Case1 - insert a user, put "tableA" as collection name
	System.out.println("Case 1...");
	//User userA = new User("1000", "apple", 54, new Date());
	//mongoOperation.save(userA, "users");

	// find
	Query findUserQuery = new Query();
	findUserQuery.addCriteria(Criteria.where("ic").is("1000"));
	User userA1 = mongoOperation.findOne(findUserQuery, User.class, "users");
	System.out.println(userA1);
	return userA1;
		
	}
	
	public void saveUser(User user) {
		
		ApplicationContext ctx = 
                new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	MongoOperations mongoOperation = 
                (MongoOperations) ctx.getBean("mongoTemplate");

	// Case1 - insert a user, put "tableA" as collection name
	System.out.println("Case 2 saving user...");
	//User userA = new User("1000", "apple", 54, new Date());
	System.out.println("persisted dao "+mongoOperation.save(user, "users"));
	}
}
