package br.com.carv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.carv.mapper.impl.PersonMapperImpl;

@Configuration
public class PersonMapperConfig {
	
	@Bean
	PersonMapperImpl personMapper() {
		return new PersonMapperImpl();
	}

}
