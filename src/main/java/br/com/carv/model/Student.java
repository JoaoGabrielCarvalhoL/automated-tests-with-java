package br.com.carv.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Student implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 0L;

	private UUID id;
	private String name; 
	private String email; 
	private List<Course> courses = new ArrayList<Course>();
	
	public Student() {
		
	}
	
	public Student(String name, String email) {
		this.name = name; 
		this.email = email;
	}
	
	public Student(String name, String email, List<Course> courses) {
		this.name = name; 
		this.email = email;
		this.courses = courses;
	}
	
	public Student(UUID id, String name, String email, List<Course> courses) {
		this.id = id;
		this.name = name; 
		this.email = email;
		this.courses = courses;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	
	
	
}
