package br.com.carv.service.impl;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import br.com.carv.entity.Person;
import br.com.carv.exception.ResourceAlreadyUsedException;
import br.com.carv.exception.ResourceNotFound;
import br.com.carv.mapper.impl.PersonMapperImpl;
import br.com.carv.payload.request.PersonPostRequest;
import br.com.carv.payload.response.PersonGetResponse;
import br.com.carv.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplRefactorTest {

	@Mock
	private PersonRepository personRepository;
	
	@Mock
	private PersonMapperImpl personMapper;

	@InjectMocks
	private PersonServiceImpl personService;

	private PersonPostRequest insertion;
	private Person entity;
	private Person entityResponse;
	
	@BeforeEach
	public void setup() {
		this.insertion = new PersonPostRequest("joao gabriel", "carvalho", "19930086", "27.joaogabriel@gmail.com",
				"joao");
		
		this.entity = new Person("joao gabriel", "carvalho", "19930086", "27.joaogabriel@gmail.com",
				"joao");
		
		this.entityResponse = new Person(UUID.randomUUID(), "joao gabriel", "carvalho", "19930086", "27.joaogabriel@gmail.com",
				"joao");
	}

	@DisplayName("Given Person Object, when save Person, then return person object persisted")
	@Test
	void testGivenPersonObject_WhenSavePerson_thenReturnPersonObjectPersisted() {

		BDDMockito.given(personRepository.save(entity)).willReturn(entityResponse);

		Person person = personRepository.save(new Person());
		System.out.println(person);
		Assertions.assertNotNull(person);
	}

	@DisplayName("Given existing email, when save Person, then throw exception")
	@Test
	void testGivenExistingEmail_WhenSavePerson_thenThrowException() {

		BDDMockito.given(personService.save(insertion)).willThrow(ResourceAlreadyUsedException.class);

		Assertions.assertThrows(ResourceAlreadyUsedException.class, () -> {
			personService.save(insertion);
		});
		Mockito.verify(personRepository, Mockito.never()).save(Mockito.any(Person.class));
	}

	@DisplayName("Given existing username, when save Person, then throw exception")
	@Test
	void testGivenExistingUsername_WhenSavePerson_thenThrowException() {

		BDDMockito.given(personService.save(insertion)).willThrow(ResourceAlreadyUsedException.class);

		Assertions.assertThrows(ResourceAlreadyUsedException.class, () -> {
			personService.save(insertion);
		});
		Mockito.verify(personRepository, Mockito.never()).save(Mockito.any(Person.class));
	}
	
	@DisplayName("Given person list, when find all, then return person list")
	@Test
	void testGivenPersonList_WhenFindAll_thenReturnPersonList() {
		
		BDDMockito.given(personRepository.findAll(ArgumentMatchers.any(PageRequest.class)))
			.willReturn(new PageImpl<Person>(Collections.emptyList()));
		
		Page<PersonGetResponse> findAll = personService.findAll(PageRequest.of(0, 20, Sort.Direction.ASC, "firstName"));
		Assertions.assertNotNull(findAll);
	}
	
	@DisplayName("Given Person ID, when find by id method, then return person object persisted")
	@Test
	void testGivenPersonID_WhenFindById_thenReturnPersonObjectPersisted() {

		BDDMockito.given(personRepository.findById(ArgumentMatchers.any(UUID.class)))
		.willReturn(Optional.of(entityResponse));

		Person person = personRepository.findById(UUID.randomUUID()).get();
		Assertions.assertNotNull(person);

	}
	
	@DisplayName("Given Person ID not found, when find by id method, then throw exception")
	@Test
	void testGivenPersonIDNotFound_WhenFindById_thenThrowException() {

		BDDMockito.given(personRepository.findById(ArgumentMatchers.any()))
		.willThrow(ResourceNotFound.class); 
		
		Assertions.assertThrows(ResourceNotFound.class, () -> {
			personRepository.findById(UUID.randomUUID());
		});
		
		Mockito.verify(personRepository, Mockito.never()).findById(UUID.randomUUID());

	}

}
