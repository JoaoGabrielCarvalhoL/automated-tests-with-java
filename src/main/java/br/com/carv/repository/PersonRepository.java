package br.com.carv.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carv.entity.Person;

public interface PersonRepository extends JpaRepository<Person, UUID>{

}
