package com.blanco.geolocalizacion.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.blanco.geolocalizacion.api.model.Sucursal;
import com.blanco.geolocalizacion.api.repository.SucursalRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class SucursalRepositoryTest {

	private static final String ERROR_VALIDACION_ATRIBUTO_LONGITUD = "El atributo longitud no debe ser nulo";
	private static final String ERROR_VALIDACION_ATRIBUTO_DIRECCION = "El atributo direccion no debe ser nulo";
	private static final String ERROR_CLAVE_DUPLICADA = "Duplicate entry 'calle 47 779 La Plata, Provincia de Buenos Aires' for key 'UK_8xmvqo01rbhula57jneec4dtd'";
	private static final Double latitud = -34.9388478;
	private static final Double longitud = -57.9591138;
	private static final Double latitud_adicional = -44.9388478;
	private static final Double longitud_adicional = -67.9591138;
	private static final String direccion = "C. 12 1249, B1900 La Plata, Provincia de Buenos Aires";
	private static final String direccionRepetida = "calle 47 779 La Plata, Provincia de Buenos Aires";
	

	@Autowired
	private SucursalRepository sucursalRepository;

	@Test
	void save() {
		
		Sucursal nuevaSucursal = sucursalRepository.save(new Sucursal(direccion,latitud,longitud));
		assertThat(nuevaSucursal).isNotNull();
		assertThat(nuevaSucursal.getId()).isNotNull();
	}

	@Test
	void findById() {

		Sucursal sucursalPersist = new Sucursal("55 num 2567", latitud, longitud);
		sucursalPersist = sucursalRepository.save(sucursalPersist);
		assertTrue(sucursalRepository.findById(sucursalPersist.getId()).isPresent());
	}

	@Test
	void SaveNotOk() {

		Sucursal sucursalSinDirreccionYlongitud = new Sucursal();
		sucursalSinDirreccionYlongitud.setLatitud(latitud);
		
		Exception exception = assertThrows(ConstraintViolationException.class, () -> {
			sucursalRepository.save(sucursalSinDirreccionYlongitud);
		});

		assertTrue(exception.getMessage().contains(ERROR_VALIDACION_ATRIBUTO_LONGITUD));
		assertTrue(exception.getMessage().contains(ERROR_VALIDACION_ATRIBUTO_DIRECCION));

	}

	// Comprueba que el constraint de BD ( column direccion: unique = true);
	@Test
	void saveSucursalWithDireccionExist() {
		sucursalRepository.save(new Sucursal(direccionRepetida,latitud, longitud));
		Exception exception = assertThrows(Exception.class, () -> {
			sucursalRepository.save(new Sucursal(direccionRepetida,latitud_adicional, longitud_adicional));
		});
		assertTrue(exception.getCause().getCause().getMessage().contains(ERROR_CLAVE_DUPLICADA));
	}

}

