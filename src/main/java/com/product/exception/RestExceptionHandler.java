package com.product.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Controlador de excepciones REST para lanzamiento de excepciones personalizadas.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Constructor por defecto para RestExceptionHandler.
	 */
    public RestExceptionHandler() {}

	/**
	 * Maneja excepciones de tipo ApiException.
	 * 
	 * @param exception la excepción ApiException lanzada
	 * @param request la solicitud web que provocó la excepción
	 * @return ResponseEntity con un objeto ExceptionResponse y un estado HTTP
	 */
	@ExceptionHandler(ApiException.class)
	protected ResponseEntity<ExceptionResponse> handleApiException(ApiException exception, WebRequest request) {

		ExceptionResponse response = new ExceptionResponse();
		
		response.setTimestamp(LocalDateTime.now());
		response.setStatus(exception.getStatus().value());
		response.setError(exception.getStatus());
		response.setMessage(exception.getMessage());
		response.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
		
		return new ResponseEntity<>(response, response.getError());
	}

	/**
	 * Maneja excepciones de tipo DBAccessException.
	 *
	 * @param exception la excepción DBAccessException lanzada
	 * @param request la solicitud web que provocó la excepción
	 * @return ResponseEntity con un objeto ExceptionResponse y un estado HTTP
	 */
    @ExceptionHandler(DBAccessException.class)
	protected ResponseEntity<ExceptionResponse> DBAccessException(DBAccessException exception, WebRequest request) {


		System.out.println(exception.getException().getLocalizedMessage());
		ExceptionResponse response = new ExceptionResponse();
		
		response.setTimestamp(LocalDateTime.now());
	    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setError(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setMessage("Error al consultar la base de datos");
		response.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());

		return new ResponseEntity<>(response, response.getError());
	}
}
