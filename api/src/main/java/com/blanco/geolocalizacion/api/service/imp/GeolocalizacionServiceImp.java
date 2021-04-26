package com.blanco.geolocalizacion.api.service.imp;

import org.springframework.stereotype.Service;

import com.blanco.geolocalizacion.api.model.GeographicCoordinate;
import com.blanco.geolocalizacion.api.service.GeolocationService;
import com.blanco.geolocalizacion.api.util.GeometricUtils;

/**
 * @author Blanco
 * @version 1.0.0
 * 
 * Clase encargada de solicitar
 * el calculo de distancias, entre coordenadas geograficas
 * a la clase GeometricUtils.
 *
 */
@Service
public class GeolocalizacionServiceImp implements GeolocationService {


	@Override
	public Double calculateDistance(GeographicCoordinate coordenadaInicial,GeographicCoordinate coordenadaFinal) {
		
		return GeometricUtils.calculateSphericalDistance(coordenadaInicial.getLat(), coordenadaInicial.getLng(), coordenadaFinal.getLat(), coordenadaFinal.getLng());
	}

}
