package br.com.carv.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.carv.entity.Person;
import br.com.carv.exception.ResourceAlreadyUsedException;
import br.com.carv.exception.ResourceNotFound;
import br.com.carv.mapper.impl.PersonMapperImpl;
import br.com.carv.payload.request.PersonPostRequest;
import br.com.carv.payload.request.PersonPutRequest;
import br.com.carv.payload.response.PersonGetResponse;
import br.com.carv.repository.PersonRepository;
import br.com.carv.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	private final PersonRepository personRepository;
	private final PersonMapperImpl personMapper;
	private final Logger logger = Logger.getLogger(PersonServiceImpl.class.getCanonicalName());

	public PersonServiceImpl(PersonRepository personRepository, PersonMapperImpl personMapper) {
		this.personRepository = personRepository;
		this.personMapper = personMapper;
	}

	@Override
	public PersonGetResponse findById(UUID id) {
		logger.log(Level.INFO, "Getting person by id: {0}", id);
		return this.personRepository.findById(id).map(personMapper::toPersonGetResponse)
				.orElseThrow(() -> new ResourceNotFound("Person not found into database. ID: " + id));
	}

	@Override
	public PersonGetResponse save(PersonPostRequest personPostRequest) {
		logger.log(Level.INFO, "Saving resource into database.");
		verifyEmail(personPostRequest);
		verifyUsername(personPostRequest);
		Person saved = this.personRepository.save(personMapper.toPerson(personPostRequest));
		return personMapper.toPersonGetResponse(saved);
	}

	@Override
	public List<PersonGetResponse> saveAll(List<PersonPostRequest> persons) {
		logger.log(Level.INFO, "Saving all resources into database.");
		List<Person> list = persons.stream().map(personMapper::toPerson).toList();
		List<Person> saved = this.personRepository.saveAll(list);
		return saved.stream().map(personMapper::toPersonGetResponse).toList();
	}

	@Override
	public PersonGetResponse update(PersonPutRequest personPutRequest) {
		logger.log(Level.INFO, "Updating resource into database.");
		Person updated = this.personRepository.saveAndFlush(personMapper.toPerson(personPutRequest));
		return personMapper.toPersonGetResponse(updated);
	}

	@Override
	public Page<PersonGetResponse> findAll(Pageable pageable) {
		logger.log(Level.INFO, "Getting all resources from database.");
		List<PersonGetResponse> list = this.personRepository.findAll(pageable).stream()
				.map(personMapper::toPersonGetResponse).toList();
		return new PageImpl<>(list, pageable, list.size());
	}

	@Override
	public void delete(UUID id) {
		logger.log(Level.WARNING, "Deleting resource from database with ID: {0}.", id);
		this.personRepository.delete(findEntityById(id));
	}

	@Override
	public Person findEntityById(UUID id) {
		logger.log(Level.INFO, "Getting entity by id: {0}", id);
		return this.personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Resource not found into database. ID: " + id));
	}

	private void verifyEmail(PersonPostRequest personPostRequest) {
		Optional<Person> findByEmail = this.personRepository.findByEmail(personPostRequest.email());

		if (findByEmail.isPresent()) {
			throw new ResourceAlreadyUsedException(
					"Resource used by another user. Identifiers {Email} must be unique.");
		}
	}

	private void verifyUsername(PersonPostRequest personPostRequest) {
		Optional<Person> findByUsername = this.personRepository.findByUsername(personPostRequest.username());

		if (findByUsername.isPresent()) {
			throw new ResourceAlreadyUsedException(
					"Resource used by another user. Identifier {Username} must be unique.");
		}
	}

}
