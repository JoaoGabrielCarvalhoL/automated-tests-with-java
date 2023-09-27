package br.com.carv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import br.com.carv.exception.ResourceNotFound;
import br.com.carv.model.Course;
import br.com.carv.service.CourseService;

class CourseServiceImplTest {
	
	private CourseService courseService;
	private List<Course> courses;
	private CourseServiceImpl service = new CourseServiceImpl();

	@BeforeEach
	void setup() {
		this.courseService = Mockito.mock(CourseService.class);
		this.courses = new ArrayList<Course>();
	}

	@Test
	void test_retrieve_courses_by_student_id_throws_exception_when_not_found_student() {
		Mockito.when(courseService.retrieveCoursesByStudentID(ArgumentMatchers.any()))
		.thenThrow(new ResourceNotFound("Student not found. Random ID"));
		
		Assertions.assertThrows(ResourceNotFound.class,  () -> {
			courseService.retrieveCoursesByStudentID(UUID.randomUUID());
		}, () -> "ResourceNotFound must be throwed");
		
	}
	
	@Test
	void test_retrieve_courses_by_student_id_throws_exception_when_not_found_student2() {
		Assertions.assertThrows(ResourceNotFound.class,  () -> {
			service.retrieveCoursesByStudentID(UUID.randomUUID());
		}, () -> "ResourceNotFound must be throwed");
		
	}
	
	@Test
	void test_retrieve_courses_by_student_id_should_return_a_list_of_courses_when_success() {
		Mockito.when(courseService.retrieveCoursesByStudentID(ArgumentMatchers.any())).thenReturn(courses);
		Assertions.assertNotNull(courses);
		Assertions.assertTrue(courses.isEmpty());
	}
	
	@Test
	void test_retrieve_courses_by_student_id_should_return_a_list_of_courses_when_success2() {
		BDDMockito.given(courseService.retrieveCoursesByStudentID(ArgumentMatchers.any())).willReturn(courses);
		Assertions.assertNotNull(courses);
		Assertions.assertTrue(courses.isEmpty());
	}
	

}
