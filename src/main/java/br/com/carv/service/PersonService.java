package br.com.carv.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.carv.entity.Person;
import br.com.carv.payload.request.PersonPostRequest;
import br.com.carv.payload.request.PersonPutRequest;
import br.com.carv.payload.response.PersonGetResponse;

public interface PersonService {
	
	PersonGetResponse findById(UUID id);
	
	PersonGetResponse save(PersonPostRequest personPostRequest);
	
	List<PersonGetResponse> saveAll(List<PersonPostRequest> persons);
	
	PersonGetResponse update(PersonPutRequest personPutRequest);
	
	Page<PersonGetResponse> findAll(Pageable pageable);
	
	void delete(UUID id);
	
	Person findEntityById(UUID id);

}
