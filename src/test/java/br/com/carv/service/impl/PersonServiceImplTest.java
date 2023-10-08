package br.com.carv.service.impl;

import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
class PersonServiceImplTest {

	@Mock
	private PersonRepository personRepository;

	@Mock
	private PersonMapperImpl personMapper;

	@InjectMocks
	private PersonServiceImpl personService;

	private PersonPostRequest insertion;
	private PersonGetResponse result;
	
	@BeforeEach
	public void setup() {
		this.insertion = new PersonPostRequest("joao gabriel", "carvalho", "19930086", "27.joaogabriel@gmail.com",
				"joao");

		this.result = new PersonGetResponse(UUID.randomUUID(), "joao gabriel", "27.joaogabriel@gmail.com");
	}

	@DisplayName("Given Person Object, when save Person, then return person object persisted")
	@Test
	void testGivenPersonObject_WhenSavePerson_thenReturnPersonObjectPersisted() {

		BDDMockito.given(personService.save(insertion)).willReturn(result);

		PersonGetResponse person = personService.save(insertion);
		Assertions.assertNotNull(person);
		Assertions.assertEquals(person.id(), result.id());
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
	@Disabled("Pageable null")
	void testGivenPersonList_WhenFindAll_thenReturnPersonList() {
		
		BDDMockito.given(personService.findAll(ArgumentMatchers.any(PageRequest.class)))
			.willReturn(new PageImpl<PersonGetResponse>(Collections.emptyList()));
		
		Page<PersonGetResponse> findAll = personService.findAll(PageRequest.of(0, 20, Sort.Direction.ASC, "firstName"));
		Assertions.assertNotNull(findAll);
	}
	
	@DisplayName("Given Person ID, when find by id method, then return person object persisted")
	@Test
	void testGivenPersonID_WhenFindById_thenReturnPersonObjectPersisted() {

		BDDMockito.given(personService.findById(UUID.randomUUID()))
		.willReturn(result);

		PersonGetResponse person = personService.findById(UUID.randomUUID());
		Assertions.assertNotNull(person);
		Assertions.assertEquals(person.id(), result.id());
	}
	
	@DisplayName("Given Person ID not found, when find by id method, then throw exception")
	@Test
	void testGivenPersonIDNotFound_WhenFindById_thenThrowException() {

		BDDMockito.given(personService.findById(UUID.randomUUID()))
		.willThrow(ResourceNotFound.class); 
		
		Assertions.assertThrows(ResourceNotFound.class, () -> {
			personService.findById(UUID.randomUUID());
		});
		
		Mockito.verify(personRepository, Mockito.never()).findById(UUID.randomUUID());

	}

}
