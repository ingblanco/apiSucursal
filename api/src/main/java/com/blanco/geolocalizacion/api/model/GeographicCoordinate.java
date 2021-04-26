package com.blanco.geolocalizacion.api.model;

/**
 * @author Blanco
 * @version 1.0.0
 * 
 * Clase que representa una coordenada
 * geografica , en el plano cartesiano.
 */
public class GeographicCoordinate {
	
	private Double latitude;
	
	private Double longitude;
	
	
	/** Constructor 
	 * @param lat
	 * @param lng
	 */
	public GeographicCoordinate(Double lat, Double lng) {
		this.latitude = lat;
		this.longitude = lng;
	}
	
	public Double getLat() {
		return latitude;
	}
	public void setLat(Double lat) {
		this.latitude = lat;
	}
	public Double getLng() {
		return longitude;
	}
	public void setLng(Double lng) {
		this.longitude = lng;
	}

}
