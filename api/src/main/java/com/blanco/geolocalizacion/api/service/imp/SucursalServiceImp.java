package com.blanco.geolocalizacion.api.service.imp;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.blanco.geolocalizacion.api.dto.SucursalDto;
import com.blanco.geolocalizacion.api.model.GeographicCoordinate;
import com.blanco.geolocalizacion.api.model.Sucursal;
import com.blanco.geolocalizacion.api.repository.SucursalRepository;
import com.blanco.geolocalizacion.api.service.GeolocationService;
import com.blanco.geolocalizacion.api.service.SucursalService;

/**
 * @author Blanco
 * @version 1.0.0
 * 
 *          Clase pertenenciente a la capa de servicio encargada de comunicarce
 *          con la capa de persistencia DAO para operaciones CRUD y responsable
 *          de consumir el servicio encargado de obtener la sucursal mas cercana
 *          a un punto determinado.
 */
@Service
public class SucursalServiceImp implements SucursalService {

	private static final Logger logger = LoggerFactory.getLogger(SucursalService.class);

	private Double distanciaMin = Double.MAX_VALUE;
	private Sucursal sucursalCercama = null;

	@Autowired
	private SucursalRepository sucursalRepository;
	@Autowired
	private GeolocationService geolocalizacionService;

	/**
	 * Metodo encargado de llamar a la capa de persistencia (DAO) para realizar la
	 * busqueda de una sucursal, mediante el id recibido por parametro. Retorna un
	 * objeto dto asociado a la entidad Sucursal.
	 * 
	 * @param id
	 * @return SucursalDto
	 */
	@Override
	public SucursalDto findById(Long id) {

		if (id == null)
			throw new IllegalArgumentException("El Atributo Direccion debe ser distinto de vacio.");

		Optional<Sucursal> sucursal = sucursalRepository.findById(id);
		if (!sucursal.isPresent()) {
			throw new NoSuchElementException("No existe la sucursal con el id: " + id);
		}
		return new SucursalDto(sucursal.get().getId(), sucursal.get().getDireccion(), sucursal.get().getLatitud(),
				sucursal.get().getLongitud());
	}

	/**
	 * Metodo encargado de llamar a la capa de persistencia (DAO) para insertar una
	 * nueva sucursal. Retorna un objeto DTO asocido a la entidad Sucursal.
	 * 
	 * @param sucursal
	 * @return SucursalDto
	 */
	@Transactional
	public SucursalDto save(Sucursal sucursal) {

		if (sucursal.getDireccion().isEmpty())
			throw new IllegalArgumentException("El Atributo Direccion debe ser distinto de vacio.");
		if (sucursal.getLatitud() == null)
			throw new IllegalArgumentException("El Atributo Latitud no debe ser nulo.");

		if (sucursal.getLongitud() == null)
			throw new IllegalArgumentException("El Atributo Longitud no debe ser nulo.");

		Sucursal sucursalExample = new Sucursal(sucursal.getDireccion(), sucursal.getLatitud(), sucursal.getLongitud());
		if (sucursalRepository.exists(Example.of(sucursalExample))) {

			logger.error("Se intento dar de alta una sucursal ya existente", sucursal);
			throw new DuplicateKeyException(
					"Ya existe Sucursal con Direccion : " + sucursal.getDireccion() + ", Latitud: "
							+ sucursal.getLatitud().toString() + " y longitud: " + sucursal.getLongitud().toString());
		}

		try {

			sucursal = sucursalRepository.save(sucursal);

		} catch (DuplicateKeyException exc) {
			logger.error("Direccion duplicada: " + sucursal.getDireccion(), exc.getMostSpecificCause());
			throw new DuplicateKeyException("Ya existe Sucursal con Direccion : " + sucursal.getDireccion());

		}

		logger.info("Se dio de alta la sucursal: ", sucursal);

		return new SucursalDto(sucursal.getId(), sucursal.getDireccion(), sucursal.getLatitud(),
				sucursal.getLongitud());
	}

	/**
	 * Metodo encargado de obtener la sucursal mas cercana al punto representado por
	 * el parametro coordenadaUsuario
	 * 
	 * @param coordenadaUsuario
	 * @return SucursalDto
	 */
	@Override
	public SucursalDto getNearbySucursal(GeographicCoordinate coordenadaUsuario) {

		if (coordenadaUsuario.getLat() == null)
			throw new IllegalArgumentException("El Atributo Latitud no debe ser nulo.");
		if (coordenadaUsuario.getLng() == null)
			throw new IllegalArgumentException("El Atributo Longitud no debe ser nulo.");

		List<Sucursal> sucursales = sucursalRepository.findAll();
		SucursalDto sucursalDto = new SucursalDto();
		if (!sucursales.isEmpty()) {

			sucursalDto = calculateDistance(sucursales, coordenadaUsuario);
			sucursales.clear();
		}

		return sucursalDto;
	}

	/**
	 * Metodo encargado de obtener la sucursal mas cercana al punto representado por
	 * el parametro coordenadaUsuario
	 * 
	 * @param sucursales
	 * @param coordenadaUsuario
	 * @return
	 */
	private SucursalDto calculateDistance(List<Sucursal> sucursales, GeographicCoordinate coordenadaUsuario) {

		sucursales.forEach(sucursal -> {

			Double distanciaActual = geolocalizacionService.calculateDistance(
					new GeographicCoordinate(sucursal.getLatitud(), sucursal.getLongitud()), coordenadaUsuario);

			if (distanciaActual.doubleValue() < distanciaMin.doubleValue()) {

				distanciaMin = distanciaActual;
				sucursalCercama = sucursal;

			}

		});

		return new SucursalDto(sucursalCercama.getId(), sucursalCercama.getDireccion(), sucursalCercama.getLatitud(),
				sucursalCercama.getLongitud());
	}
}
