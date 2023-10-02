package br.com.carv.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.carv.entity.Person;

@DataJpaTest
class PersonRepositoryTest {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Test
	@DisplayName("Given a person object, when it is saved, it must return the persisted object.")
	void testGivenPersonObject_whenSave_thenReturnSavedPerson() {
		
		Person person = new Person("joao gabriel", "carvalho", "19930086", 
				"27.joaogabriel@gmail.com", "joao"); 
		
		Person savedPerson = personRepository.save(person);
		
		Assertions.assertNotNull(savedPerson);
	}
	
	@Test
	@DisplayName("Given a person list, when find all method, it must return the list of persisted objects.")
	void testGivenPersonList_whenFindAll_thenReturnPersonList() {
		
		Person person = new Person("joao gabriel", "carvalho", "19930086", 
				"27.joaogabriel@gmail.com", "joao"); 
		
		personRepository.save(person);
		
		List<Person> list = personRepository.findAll();
		Assertions.assertNotNull(list);
		Assertions.assertTrue(list.size() > 0);
		
	}
	
	@Test
	@DisplayName("Given a person object, when find by id method, it must return object.")
	void testGivenPersonObject_whenFindById_thenReturnPersonObject() {
		
		Person person = new Person("joao gabriel", "carvalho", "19930086", 
				"27.joaogabriel@gmail.com", "joao"); 
		
		Person saved = personRepository.save(person);
		
		UUID id = saved.getId();
		
		Optional<Person> findById = personRepository.findById(id);
		Assertions.assertEquals(findById.get().getId(), id);
		
		
	}
	
	@Test
	@DisplayName("Given a person object, when find by email method, it must return object.")
	void testGivenPersonObject_whenFindByEmail_thenReturnPersonObject() {
		
		Person person = new Person("joao gabriel", "carvalho", "19930086", 
				"27.joaogabriel@gmail.com", "joao"); 
		
		personRepository.save(person);
		
		Optional<Person> email = personRepository.findByEmail("27.joaogabriel@gmail.com");
		Assertions.assertEquals(email.get().getEmail(), person.getEmail());
	
	}
	
	@Test
	@DisplayName("Given a person object, when find by username method, it must return object.")
	void testGivenPersonObject_whenFindByUsername_thenReturnPersonObject() {
		
		Person person = new Person("joao gabriel", "carvalho", "19930086", 
				"27.joaogabriel@gmail.com", "joao"); 
		
		personRepository.save(person);
		
		Optional<Person> username = personRepository.findByUsername("joao");
		Assertions.assertEquals(username.get().getUsername(), person.getUsername());
	
	}
	
	@Test
	@DisplayName("Given a person object, when delete method, it must remove object.")
	void testGivenPersonObject_whenDelete_thenRemovePersonObject() {
		
		Person person = new Person("joao gabriel", "carvalho", "19930086", 
				"27.joaogabriel@gmail.com", "joao"); 
		
		personRepository.save(person);
		
		personRepository.delete(person);
		Optional<Person> empty = personRepository.findById(person.getId());
		Assertions.assertEquals(Optional.empty(), empty);
	
	}
	

}
