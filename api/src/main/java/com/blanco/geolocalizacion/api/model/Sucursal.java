package com.blanco.geolocalizacion.api.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Blanco
 * 
 *         Clase que representa la entidad Sucursal del modelo de datos de la
 *         aplicacion.
 *
 */

@Entity
public class Sucursal {

	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "El atributo direccion no debe ser nulo")
	@Column(unique = true, nullable = false)
	private String direccion;
	@NotNull(message = "El atributo Latitud no debe ser nulo")
	@Column(nullable = false)
	private Double latitud;
	@NotNull(message = "El atributo longitud no debe ser nulo")
	@Column(nullable = false)
	private Double longitud;

	public Sucursal() {

	}

	public Sucursal(String direccion, Double latitud, Double longitud) {
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;

	}

	public Sucursal(Long id, String direccion, Double latitud, Double longitud) {
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.id = id;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof Sucursal))
			return false;

		Sucursal sucursal = (Sucursal) obj;

		return super.equals(sucursal) && Objects.equals(this.direccion, sucursal.direccion)
				&& Objects.equals(this.id, sucursal.id) && Objects.equals(this.latitud, sucursal.latitud)
				&& Objects.equals(this.longitud, sucursal.longitud);
	}

	@Override
	public String toString() {
		return "Direccion: ".concat(this.direccion).concat(" Latitud: ").concat(this.latitud.toString())
				.concat(" Longitud: ").concat(this.longitud.toString());
	}
}
