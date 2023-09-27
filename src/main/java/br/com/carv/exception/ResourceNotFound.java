package br.com.carv.exception;

import java.io.Serial;

public class ResourceNotFound extends RuntimeException {
	
	@Serial
	private static final long serialVersionUID = 0L;
	public ResourceNotFound(String message) {
		super(message);
	}
}
