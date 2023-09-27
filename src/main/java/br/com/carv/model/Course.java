package br.com.carv.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class Course implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 0L;
	
	private UUID id;
	private String name; 

	public Course() {
		
	}
	
	public Course(String name) {
		this.name = name;
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
	
	
}
