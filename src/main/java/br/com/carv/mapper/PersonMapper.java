package br.com.carv.mapper;

import br.com.carv.entity.Person;
import br.com.carv.payload.request.PersonPostRequest;
import br.com.carv.payload.request.PersonPutRequest;
import br.com.carv.payload.response.PersonGetResponse;

public interface PersonMapper {
	
	public PersonGetResponse toPersonGetResponse(Person person);
	
	public Person toPerson(PersonPutRequest personPutRequest);
	
	public Person toPerson(PersonPostRequest personPostRequest);
	
}