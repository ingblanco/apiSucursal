package com.blanco.geolocalizacion.api.util;

/**
 * @author Federico Blanco.
 * @version 1.0.0
 * 
 *  Clase encargada de calcular la distancia entre dos puntos
 *  geograficos. 
 *  
 *  El calculo se hace mediante la formula de Haversin
 *  que asume que tierra es una esfera perfecta.
 *  
 */
public class GeometricUtils {

	final static Integer EQUATORIAL_RADIOS_MTS = 6378137;

	/** calculoDistanciaEsferica
	 * @param lat1  Latitud del punto origen
	 * @param lng1  longitud del punto origen
	 * @param lat2  Latitud del punto  destino
	 * @param lng2  longitud del punto destino
	 * 
	 */
	public static Double calculateSphericalDistance(Double lat1, Double lng1, Double lat2, Double lng2) {

		return HaversineEquation(lat1, lng1, lat2, lng2 );
	}

	/**  Aplica la ecuacion de Harversine
	 * para el calculo de distancia (Geodesica) 
	 * entre dos puntos de una esfera perfecta.
	 * Dicha distancia se expresa en metros.
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	private static Double HaversineEquation(Double lat1, Double lng1, Double lat2, Double lng2) {

		Double latDistance = toRadian(lat2 - lat1);
		Double lonDistance = toRadian(lng2 - lng1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(toRadian(lat1)) * Math.cos(toRadian(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return EQUATORIAL_RADIOS_MTS * c;

	}

	/**
	 * Transformacion de unidad cartesiana a
	 * unidad Polar.
	 * 
	 * @param value 
	 * @return CoordenadaPolar
	 */
	private static Double toRadian(Double value) {
		return value * Math.PI / 180;
	}

}
