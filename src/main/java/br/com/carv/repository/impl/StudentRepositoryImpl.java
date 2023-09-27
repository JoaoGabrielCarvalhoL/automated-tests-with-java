package br.com.carv.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import br.com.carv.exception.ResourceNotFound;
import br.com.carv.model.Course;
import br.com.carv.model.Student;
import br.com.carv.repository.StudentRepository;

public class StudentRepositoryImpl implements StudentRepository {

	private final Logger logger = Logger.getLogger(StudentRepositoryImpl.class.getCanonicalName());
	private List<Student> students = new ArrayList<Student>();
	
	public StudentRepositoryImpl() {
		populate();
	}
	
	@Override
	public List<Student> findAll() {
		logger.info("Getting all students");
		return this.students;
	}

	@Override
	public Student findByID(UUID id) {
		logger.info("Find Student by id: " + id);
		return this.students.stream().filter(student -> student.getId().equals(id)).findFirst()
		.orElseThrow(() -> new ResourceNotFound("Student not found. Id: " + id));
	}

	@Override
	public void delete(UUID id) {
		logger.info("Deleting student by id: " + id);
		Student student = findByID(id);
		this.students.remove(student);
	}

	@Override
	public Student add(Student student) {
		logger.info("Add student");
		student.setId(UUID.randomUUID());
		this.students.add(student);
		return student;
	}
	
	private void populate() {
		this.students.add(new Student(UUID.randomUUID(), "João Gabriel", "27.joaogabriel@gmail.com", Arrays.asList(new Course("OpenGL"))));
		this.students.add(new Student(UUID.randomUUID(), "Laís Mansano", "lais@gmail.com", Arrays.asList(new Course("Compilers"))));
		this.students.add(new Student(UUID.randomUUID(), "Ruy Barbosa", "ruy@gmail.com", Arrays.asList(new Course("SpringFramework"))));
		this.students.add(new Student(UUID.randomUUID(), "Steve Rogers", "steve@gmail.com", Arrays.asList(new Course("JakartaEE"))));
		this.students.add(new Student(UUID.randomUUID(), "James Barnes", "jbarnes@gmail.com", Arrays.asList(new Course("JUnit"))));
		this.students.add(new Student(UUID.randomUUID(), "Tony Stark", "stark@gmail.com", Arrays.asList(new Course("QuarkusFramework"))));
		this.students.add(new Student(UUID.randomUUID(), "Bruce Banner", "banner@gmail.com", Arrays.asList(new Course("JasperReports"))));
		this.students.add(new Student(UUID.randomUUID(), "Johnny Blaze", "lais@gmail.com", Arrays.asList(new Course("Postgres"))));
		this.students.add(new Student(UUID.randomUUID(), "Peter Parker", "peterparker@gmail.com", Arrays.asList(new Course("Photography"))));
		this.students.add(new Student(UUID.randomUUID(), "Bruce Wayne", "bruce@gmail.com", Arrays.asList(new Course("Reflections"))));

	}

}
