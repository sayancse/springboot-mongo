package com.boot.rest.kafka;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.boot.rest.UserDao;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Consumer {
	
	@Autowired
	private UserDao userDao;

    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @KafkaListener(topics = "sayantopic", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        User user = new ObjectMapper().readValue(message, User.class);
        userDao.saveUser(user);
        System.out.println("sent user from consumer to dao");
    }
}
