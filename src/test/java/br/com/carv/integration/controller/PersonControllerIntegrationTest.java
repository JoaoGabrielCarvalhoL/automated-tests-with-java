package br.com.carv.integration.controller;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.carv.config.TestConfig;
import br.com.carv.integration.testcontainers.AbstractIntegrationTest;
import br.com.carv.payload.request.PersonPostRequest;
import br.com.carv.payload.response.PersonGetResponse;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PersonControllerIntegrationTest extends AbstractIntegrationTest {

	private static RequestSpecification requestSpecification;
	private static ObjectMapper objectMapper;
	private static PersonPostRequest request;
	private static String idPerson;

	@BeforeAll
	static void setup() {

		requestSpecification = new RequestSpecBuilder().setBasePath("/v1/person").setPort(TestConfig.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL)).addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		request = new PersonPostRequest("Joao Gabriel", "Carvalho", "19930086", "27.joao1gabriel@gmail.com", "joao123");
	}

	@Order(1)
	@DisplayName("Given a person object, when save person method, then return saved person")
	@Test
	void integrationTest_whenCreatePerson_thenReturnPersonObject()
			throws JsonMappingException, JsonProcessingException {
		String json = RestAssured.given().spec(requestSpecification).contentType(TestConfig.CONTENT_TYPE_JSON)
				.body(request).when().post().then().statusCode(201).extract().body().asString();

		PersonGetResponse resultPerson = objectMapper.readValue(json, PersonGetResponse.class);
		Assertions.assertNotNull(resultPerson);
		Assertions.assertEquals(resultPerson.firstName(), request.firstName());
		Assertions.assertEquals(resultPerson.email(), request.email());
		idPerson = String.valueOf(resultPerson.id());
	}

	@Order(2)
	@DisplayName("Given a person ID, when find by id method, then return persisted person")
	@Test
	void integrationTestGivenPersonID_whenFindById_thenReturnPersistedPerson()
			throws JsonMappingException, JsonProcessingException {
		
		String json = RestAssured.given().spec(requestSpecification).pathParam("id", idPerson).when().get("{id}").then().statusCode(200).extract()
				.body().asString();
		
		PersonGetResponse resultPerson = objectMapper.readValue(json, PersonGetResponse.class);
		Assertions.assertNotNull(resultPerson);
		Assertions.assertEquals(resultPerson.firstName(), request.firstName());
		Assertions.assertEquals(resultPerson.email(), request.email());
	}
	
	@Order(3)
	@DisplayName("Given a person list, when find all method, then return person list")
	@Test
	void integrationTestGivenPersonList_whenFindAll_thenReturnPersonList()
			throws JsonMappingException, JsonProcessingException {
		
		Pageable pageable = PageRequest.of(0, 20, Sort.Direction.ASC, "firstName");
		objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		
		String json = RestAssured.given().spec(requestSpecification).contentType(TestConfig.CONTENT_TYPE_JSON)
				.body(pageable).when().get().then().statusCode(200).extract().body().asString();
	
		Page<PersonGetResponse> page = new PageImpl<>(Arrays.asList(objectMapper.readValue(json, PersonGetResponse[].class)));
		Assertions.assertNotNull(page);
	}
	
	@Order(4)
	@DisplayName("Given a person ID, when delete, then return no content")
	@Test
	void integrationTestGivenPersonID_whenDelete_thenReturnNoContent()
			throws JsonMappingException, JsonProcessingException {
		
		RestAssured.given().spec(requestSpecification).pathParam("id", idPerson).when().delete("{id}").then().statusCode(204);
	}

}
