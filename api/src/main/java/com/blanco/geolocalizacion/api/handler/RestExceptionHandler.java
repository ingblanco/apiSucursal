package com.blanco.geolocalizacion.api.handler;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.blanco.geolocalizacion.api.error.*;
import com.blanco.geolocalizacion.api.exception.InvalidDataException;

/**
 * @author Blanco
 * @version 1.0.0
 * 
 * Clase encargada de manejar, globlalmente
 * las diferentes excepciones arrojadas 
 * en las capas de Servicios y Dao. 
 *
 */
/**
 * @author Blanco
 *
 */
/**
 * @author Blanco
 *
 */
/**
 * @author Blanco
 *
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	private static Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler
	protected ResponseEntity<ErrorResponse> handleException(NoSuchElementException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return buildResponseEntity(httpStatus, exc);
	}

	@ExceptionHandler
	protected ResponseEntity<ErrorResponse> handleException(DuplicateKeyException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return buildResponseEntity(httpStatus, exc);
	}

	@ExceptionHandler
	protected ResponseEntity<ErrorResponse> handleException(IllegalArgumentException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return buildResponseEntity(httpStatus, exc);
	}

	@ExceptionHandler
	protected ResponseEntity<ErrorResponse> handleException(InvalidDataException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		List<String> errors = exc.getResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());
		return buildResponseEntity(httpStatus, new RuntimeException("Informacion invalida"), errors);
	}

	@ExceptionHandler
	protected ResponseEntity<ErrorResponse> handleException(MethodArgumentTypeMismatchException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return buildResponseEntity(httpStatus, new RuntimeException("Tipo de Argumento invalido"));
	}

	@ExceptionHandler
	protected ResponseEntity<ErrorResponse> handleException(Exception exc) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return buildResponseEntity(httpStatus,
				new RuntimeException("Se presento un problema, reporte e intente luego."));
	}

	private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Exception exc) {

		logger.error("Error: ", exc.getCause());
		return buildResponseEntity(httpStatus, exc, null);
	}

	/**
	 * Metodo encargado de generar el objeto que representa un tipo de respuesta de
	 * error customizado.
	 * 
	 * @param httpStatus
	 * @param exc
	 * @param errors
	 * @return
	 */
	private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Exception exc,
			List<String> errors) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(exc.getMessage());
		error.setStatus(httpStatus.value());
		error.setTimestamp(new Date());
		error.setErrors(errors);
		logger.error("Error: ", exc.getCause());
		return new ResponseEntity<>(error, httpStatus);

	}

}
