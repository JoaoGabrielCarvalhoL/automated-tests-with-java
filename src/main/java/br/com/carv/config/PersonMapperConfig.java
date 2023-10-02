package br.com.carv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.carv.mapper.PersonMapper;

@Configuration
public class PersonMapperConfig {
	
	@Bean
	PersonMapper personMapper() {
		return new PersonMapper();
	}

}
