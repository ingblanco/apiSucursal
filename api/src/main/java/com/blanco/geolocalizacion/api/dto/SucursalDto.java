package com.blanco.geolocalizacion.api.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Blanco
 * @version
 * 
 * Clase encargada de representar,
 * de cara al frontend, la entidad
 * Sucursal del modelo de datos.
 *
 */
public class SucursalDto {
	
	@ApiModelProperty(hidden = true)
	private Long id;
	@ApiModelProperty(position = 0)
	private String direccion;
	@ApiModelProperty(position = 1)
	private Double latitud;
	@ApiModelProperty(position = 2)
	private Double longitud;
	
	public SucursalDto() {}

	public SucursalDto(Long id, String direccion, Double latitud, Double longitud) {
		this.id = id;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((latitud == null) ? 0 : latitud.hashCode());
		result = prime * result + ((longitud == null) ? 0 : longitud.hashCode());
		return result;
	}
	@Override
	public String toString() {
		return "SucursalDto [id=" + id + ", direccion=" + direccion + ", latitud=" + latitud + ", longitud=" + longitud
				+ "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SucursalDto other = (SucursalDto) obj;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (latitud == null) {
			if (other.latitud != null)
				return false;
		} else if (!latitud.equals(other.latitud))
			return false;
		if (longitud == null) {
			if (other.longitud != null)
				return false;
		} else if (!longitud.equals(other.longitud))
			return false;
		return true;
	}

}
