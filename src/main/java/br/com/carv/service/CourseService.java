package br.com.carv.service;

import java.util.List;
import java.util.UUID;

import br.com.carv.model.Course;

public interface CourseService {
	
	List<Course> retrieveCoursesByStudentID(UUID student);
	
	List<Course> retrieveCoursesByNameByStudentID(UUID student, String name);
}
