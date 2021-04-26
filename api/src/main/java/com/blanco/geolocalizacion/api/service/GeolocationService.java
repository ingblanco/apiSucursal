package com.blanco.geolocalizacion.api.service;

import com.blanco.geolocalizacion.api.model.GeographicCoordinate;

public interface GeolocationService {

	public Double calculateDistance(GeographicCoordinate initialCoordinate,GeographicCoordinate finalCoordinate);
	
}
