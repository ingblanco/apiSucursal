package com.blanco.geolocalizacion.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blanco.geolocalizacion.api.model.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {

	public List<Sucursal> findByDireccionAndLatitudAndLongitud(String direccion , Double latitud, Double longitud);
}
