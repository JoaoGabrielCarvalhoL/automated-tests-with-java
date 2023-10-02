package br.com.carv.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.carv.entity.Person;

public interface PersonRepository extends JpaRepository<Person, UUID> {
	
	Optional<Person> findByEmail(String email);
	Optional<Person> findByUsername(String username);
	
	@Query("select p from Person p where p.postalCode =:postalCode")
	Person findByPostalCode(@Param("postalCode") String postalCode);

}
