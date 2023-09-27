package br.com.carv.service.impl;

import br.com.carv.exception.InvalidArgumentsException;
import br.com.carv.model.Student;
import br.com.carv.repository.StudentRepository;
import br.com.carv.repository.impl.StudentRepositoryImpl;
import br.com.carv.service.StudentService;

public class StudentServiceImpl implements StudentService {

	private final StudentRepository repository = new StudentRepositoryImpl();
	
	@Override
	public Student create(Student student) {
		if (student.getName().isBlank() || student.getEmail().isBlank() || student.getCourses() == null) {
			throw new InvalidArgumentsException("The fields cannot be null or empty or blanks");
		}
		return this.repository.add(student);
	}

}
