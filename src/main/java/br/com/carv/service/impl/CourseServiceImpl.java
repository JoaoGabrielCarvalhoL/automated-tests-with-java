package br.com.carv.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import br.com.carv.exception.ResourceNotFound;
import br.com.carv.model.Course;
import br.com.carv.repository.StudentRepository;
import br.com.carv.repository.impl.StudentRepositoryImpl;
import br.com.carv.service.CourseService;

public class CourseServiceImpl implements CourseService{
	
	private final Logger logger = Logger.getLogger(CourseServiceImpl.class.getCanonicalName());
	private final StudentRepository repository = new StudentRepositoryImpl();

	@Override
	public List<Course> retrieveCoursesByStudentID(UUID student) {
		logger.info("Getting all courses from Student id: " + student);
		return this.repository.findByID(student).getCourses();
	}

	@Override
	public List<Course> retrieveCoursesByNameByStudentID(UUID student, String name) {
		logger.info("Getting courses by: " + name + " from Student id: " + student);
		List<Course> courses = this.repository.findByID(student).getCourses();
		return courses.stream().filter(course -> course.getName().equals(name))
		.findFirst().map(result -> Arrays.asList(result))
		.orElseThrow(() -> new ResourceNotFound("Not found. Student Id: " + student + " - Param: " + name));
	}

}
