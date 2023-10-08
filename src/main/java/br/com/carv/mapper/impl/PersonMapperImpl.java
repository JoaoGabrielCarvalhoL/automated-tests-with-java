package br.com.carv.mapper.impl;

import org.springframework.stereotype.Service;

import br.com.carv.entity.Person;
import br.com.carv.mapper.PersonMapper;
import br.com.carv.payload.request.PersonPostRequest;
import br.com.carv.payload.request.PersonPutRequest;
import br.com.carv.payload.response.PersonGetResponse;

@Service
public class PersonMapperImpl implements PersonMapper{
	
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
