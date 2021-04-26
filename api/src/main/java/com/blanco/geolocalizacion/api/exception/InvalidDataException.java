package com.blanco.geolocalizacion.api.exception;

import org.springframework.validation.BindingResult;

/**
 * @author Blanco
 * @version 1.0.0
 * 
 * Clase encargada de obtener todas
 * las validaciones de datos definidas en 
 * los objetos de negocio, por medio de las funcionalidades
 * de la biblioteca javax.validation 
 * 
 */
public class InvalidDataException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final transient BindingResult result;

	public InvalidDataException(BindingResult result) {
		super();
		this.result = result;
	}

	public InvalidDataException(String message, BindingResult result) {
		super(message);
		this.result = result;
	}

	public BindingResult getResult() {
		return result;
	}
}
