package com.blanco.geolocalizacion.api.error;

import java.util.Date;
import java.util.List;

/**
 * @author Blanco
 * @version 1.0.0
 * 
 * Clase encargada de representar 
 * un esquema de error customizado,
 * el cual reemplaza a la estrucutura por default
 * de spring , el cual es enviado
 * al front end en formato Json.
 *
 */
public class ErrorResponse {

	private int status;

	private String message;

	private Date timestamp;

	private List<String> errors;

	public ErrorResponse() {

	}

	public ErrorResponse(int status, String message, Date timestamp, List<String> errors) {
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
		this.errors = errors;
	}

	public ErrorResponse(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
