package br.com.carv.initialization;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.carv.payload.request.PersonPostRequest;
import br.com.carv.service.PersonService;

@Component
public class Initialization implements CommandLineRunner {
	
	private final PersonService personService;
	List<PersonPostRequest> persons = new ArrayList<>();
	
	public Initialization(PersonService personService) {
		this.personService = personService;
		populate();
	}

	@Override
	public void run(String... args) throws Exception {
		this.personService.saveAll(persons);
	}
	
	public void populate( ) {
		PersonPostRequest personPostRequest = 
				new PersonPostRequest("joao", "gabriel", "19930086", "27.joaogabriel@gmail.com", "joaocarv");
		this.persons.add(personPostRequest);
		
		PersonPostRequest personPostRequest2 = 
				new PersonPostRequest("lais", "mansano", "19930086", "lais@gmail.com", "lais");
		this.persons.add(personPostRequest2);
		
		PersonPostRequest personPostRequest3 = 
				new PersonPostRequest("Bruce", "Wayne", "00000000", "bruce@gmail.com", "bruce");
		this.persons.add(personPostRequest3);
		
		PersonPostRequest personPostRequest4 = 
				new PersonPostRequest("Steve", "Rogers", "00000001", "steve@gmail.com", "steve");
		this.persons.add(personPostRequest4);
	}

}
