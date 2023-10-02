package br.com.carv.mapper;

import org.springframework.stereotype.Component;

import br.com.carv.entity.Person;
import br.com.carv.payload.request.PersonPostRequest;
import br.com.carv.payload.request.PersonPutRequest;
import br.com.carv.payload.response.PersonGetResponse;

@Component
public class PersonMapper {
	
	public PersonGetResponse toPersonGetResponse(Person person) {
		return new PersonGetResponse(person.getId(), person.getFirstName(), 
				person.getEmail());
	}
	
	public Person toPerson(PersonPutRequest personPutRequest) {
		return new Person(personPutRequest.firstName(), personPutRequest.lastName(), 
				personPutRequest.postalCode(), personPutRequest.email(), personPutRequest.username());
	}
	
	public Person toPerson(PersonPostRequest personPostRequest) {
		return new Person(personPostRequest.firstName(), personPostRequest.lastName(), 
				personPostRequest.postalCode(), personPostRequest.email(), personPostRequest.username());
	}

}
