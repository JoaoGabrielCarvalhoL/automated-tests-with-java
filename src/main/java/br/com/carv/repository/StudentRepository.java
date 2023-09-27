package br.com.carv.repository;

import java.util.List;
import java.util.UUID;

import br.com.carv.model.Student;

public interface StudentRepository {

	List<Student> findAll();
	
	Student findByID(UUID id);
	
	void delete(UUID id);
	
	Student add(Student student);
	
	
	
}
