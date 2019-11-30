package com.boot.rest.kafka;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Document(collection = "users")
public class User {
		
	@Id
	private String id;
	
	@Indexed
	private String ic;
	
	private String name;
	
	private int age;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdDate;

	public User(String ic, String name, int age, Date createdDate) {
		super();
		this.ic = ic;
		this.name = name;
		this.age = age;
		this.createdDate = createdDate;
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIc() {
		return ic;
	}

	public void setIc(String ic) {
		this.ic = ic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	//getter and setter methods
	
}