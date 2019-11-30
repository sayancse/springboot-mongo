package com.test;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import com.test.model.Student;

@RestController
@EnableAutoConfiguration
public class Example {

	@RequestMapping("/bootrest")
	Student home() {
		Student s = new Student();
		s.setId(11);
		s.setName("Sayan");
		s.setAge("28");
		s.setCity("Siliguri");
		return s;
	}

	public static void main(String[] args) {
		SpringApplication.run(Example.class, args);
	}

}
