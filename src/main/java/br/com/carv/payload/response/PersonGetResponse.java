package br.com.carv.payload.response;

import java.util.UUID;

public record PersonGetResponse(
		UUID id,
		String firstName, 
		String email) {

}
