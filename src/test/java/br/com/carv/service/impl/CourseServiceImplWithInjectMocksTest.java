package br.com.carv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import br.com.carv.exception.ResourceNotFound;
import br.com.carv.model.Course;
import br.com.carv.service.CourseService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CourseServiceImplWithInjectMocksTest {
	
	@Mock
	private CourseService courseService;
	private List<Course> courses;

	@BeforeEach
	void setup() {
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
	void test_retrieve_courses_by_student_id_should_return_a_list_of_courses_when_success() {
		Mockito.when(courseService.retrieveCoursesByStudentID(ArgumentMatchers.any())).thenReturn(courses);
		Assertions.assertNotNull(courses);
		Assertions.assertTrue(courses.isEmpty());
	}


}
