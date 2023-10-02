package br.com.carv.controller.impl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carv.controller.PersonController;
import br.com.carv.payload.request.PersonPostRequest;
import br.com.carv.payload.request.PersonPutRequest;
import br.com.carv.payload.response.PersonGetResponse;
import br.com.carv.service.PersonService;
import jakarta.validation.Valid;

@RequestMapping("/v1/person")
@RestController
public class PersonControllerImpl implements PersonController {

	private final PersonService personService;
	
	public PersonControllerImpl(PersonService personService) {
		this.personService = personService;
	}

	@Override
	public ResponseEntity<PersonGetResponse> save(@Valid PersonPostRequest personPostRequest) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(this.personService.save(personPostRequest));
	}

	@Override
	public ResponseEntity<Page<PersonGetResponse>> findAll(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.personService.findAll(pageable));
	}

	@Override
	public ResponseEntity<Void> update(@Valid PersonPutRequest personPutRequest) {
		this.personService.update(personPutRequest);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<PersonGetResponse> findById(UUID id) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.personService.findById(id));
	}

	@Override
	public ResponseEntity<Void> delete(UUID id) {
		this.personService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
