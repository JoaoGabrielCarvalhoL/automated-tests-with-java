package br.com.carv.controller.impl;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.carv.exception.ResourceNotFound;
import br.com.carv.payload.request.PersonPostRequest;
import br.com.carv.payload.response.PersonGetResponse;
import br.com.carv.service.PersonService;

@WebMvcTest
public class PersonControllerImplTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonService personService;

	@Autowired
	private ObjectMapper objectMapper;

	private PersonPostRequest postRequest;

	private PersonGetResponse getResponse;

	@BeforeEach
	void setup() {
		this.postRequest = new PersonPostRequest("Joao Gabriel", "Carvalho", "19930086", "27.joaogabriel@gmail.com",
				"joao");
		this.getResponse = new PersonGetResponse(UUID.fromString("f07a9924-78cb-4a3a-9689-b05faf55b7f5"),
				"Joao Gabriel", "27.joaogabriel@gmail.com");
	}

	@DisplayName("Given a person object, when save person method, then return saved person")
	@Test
	void testGivenPersonObject_whenSavePerson_thenReturnSavedPerson() throws JsonProcessingException, Exception {
		BDDMockito.given(personService.save(any(PersonPostRequest.class)))
				.willReturn(getResponse);

		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/v1/person")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(postRequest)));

		response.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(getResponse.id().toString())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(getResponse.firstName())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(getResponse.email())));
	}

	@DisplayName("Given list of persons, when find all method, then return person list")
	@Test
	void testGivenListOfPersons_whenFindAllPersons_thenReturnPersonList() throws Exception {
		List<PersonGetResponse> persons = new ArrayList<>();
		persons.add(getResponse);
		Page<PersonGetResponse> page = new PageImpl<>(persons);
		
		BDDMockito.given(personService.findAll(Mockito.any(Pageable.class))).willReturn(page);
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/v1/person")
				.contentType(MediaType.APPLICATION_JSON_VALUE));
		
		response.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@DisplayName("Given a person ID, when find by id method, then return persisted person")
	@Test
	void testGivenPersonID_whenFindByID_thenReturnPersistedPerson() throws Exception {
		
		String id = getResponse.id().toString();
		
		BDDMockito.given(personService.findById(UUID.fromString(id))).willReturn(getResponse);
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/v1/person/{id}", id)
				.contentType(MediaType.APPLICATION_JSON_VALUE));
		
		response.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(getResponse.id().toString())))
		.andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(getResponse.firstName())));
	}
	
	@DisplayName("Given a person ID, when find by id method, then throw exception")
	@Test
	void testGivenPersonID_whenFindByID_thenThrowException() throws Exception {
		
		String id = UUID.randomUUID().toString();
		
		BDDMockito.given(personService.findById(UUID.fromString(id))).willThrow(ResourceNotFound.class);
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/v1/person/{id}", id)
				.contentType(MediaType.APPLICATION_JSON_VALUE));
		
		response.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isBadRequest());

	}
	
	@DisplayName("Given a person ID, when delete method, then return no content")
	@Test
	void testGivenPersonID_whenDelete_thenReturnNoContent() throws Exception {
		
		String id = UUID.randomUUID().toString();
		
		BDDMockito.willDoNothing().given(personService).delete(UUID.fromString(id));
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/v1/person/{id}", id));
		
		response.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNoContent());
		
	}
}
