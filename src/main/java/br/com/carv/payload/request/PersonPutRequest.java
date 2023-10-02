package br.com.carv.payload.request;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PersonPutRequest(
		
		UUID id,
		@NotBlank(message = "The field first name cannot be empty.")
		String firstName,
		@NotBlank(message = "The field last name cannot be empty.")
		String lastName,
		@NotBlank(message = "The field postal code cannot be empty.")
		String postalCode,
		@NotBlank(message = "The field email cannot be empty.")
		@Email
		String email,
		@NotBlank(message = "The field username cannot be empty and must be unique.")
		String username) {

}
