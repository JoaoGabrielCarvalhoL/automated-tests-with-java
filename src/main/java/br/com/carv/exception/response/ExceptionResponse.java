package br.com.carv.exception.response;

import java.time.LocalDateTime;

public record ExceptionResponse(
		String title,
        Integer status,
        String details,
        LocalDateTime timestamp) {

}
