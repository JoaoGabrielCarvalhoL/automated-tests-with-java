package br.com.carv.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.carv.payload.request.PersonPostRequest;
import br.com.carv.payload.request.PersonPutRequest;
import br.com.carv.payload.response.PersonGetResponse;
import jakarta.validation.Valid;

public interface PersonController {

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	ResponseEntity<PersonGetResponse> save(@RequestBody @Valid PersonPostRequest personPostRequest);
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<Page<PersonGetResponse>> findAll(Pageable pageable);
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	ResponseEntity<Void> update(@RequestBody @Valid PersonPutRequest personPutRequest);
	
	@GetMapping(value ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<PersonGetResponse> findById(@PathVariable("id") UUID id);
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	ResponseEntity<Void> delete(@PathVariable("id") UUID id);
	
}
