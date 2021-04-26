package com.blanco.geolocalizacion.api.service;

import com.blanco.geolocalizacion.api.dto.SucursalDto;
import com.blanco.geolocalizacion.api.model.GeographicCoordinate;
import com.blanco.geolocalizacion.api.model.Sucursal;

public interface SucursalService {
	
	public SucursalDto findById(Long id);
	
	public SucursalDto save(Sucursal sucursal);
	
	public SucursalDto getNearbySucursal (GeographicCoordinate coordenadas);

}
