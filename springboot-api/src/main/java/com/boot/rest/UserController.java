package com.boot.rest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.boot.rest.UserDao;
import com.boot.rest.kafka.Producer;
import com.boot.rest.kafka.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/users")
public class UserController
{
    @Autowired
    private UserDao userDao;
    @Autowired
    private Producer producer;
     
    @GetMapping(path="/getUser", produces = "application/json")
    public User getEmployees()
    {
        return userDao.getUser();
    }
     
    @PostMapping(path= "/sendUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> sendUser(@RequestBody User user)
    {
    	System.out.println("UserController sendUser..."+user);
    	ObjectMapper Obj = new ObjectMapper(); 
        String jsonStr = null;
		try {
			jsonStr = Obj.writeValueAsString(user);
			System.out.println("json string user "+jsonStr);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        producer.sendMessage(jsonStr);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                                    .path("/{id}")
//                                    .buildAndExpand(employee.getId())
//                                    .toUri();
         
        return ResponseEntity.ok("message sent to topic");
    }
    
    /*
    @PostMapping(path= "/addUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addUser(@RequestBody User user)
    {
        Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
        employee.setId(id);
         
        employeeDao.addEmployee(employee);
         
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(employee.getId())
                                    .toUri();
         
        return ResponseEntity.created(location).build();
    }
    */
}
