package com.blanco.geolocalizacion.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.blanco.geolocalizacion.api.controller.SucursalController;
import com.blanco.geolocalizacion.api.dto.SucursalDto;
import com.blanco.geolocalizacion.api.model.GeographicCoordinate;
import com.blanco.geolocalizacion.api.model.Sucursal;
import com.blanco.geolocalizacion.api.repository.SucursalRepository;
import com.blanco.geolocalizacion.api.service.imp.SucursalServiceImp;

/**
 * @author blanco Clase encargada de realizar Test Unitarios sobre los
 *         diferentes EndPonits del servicio.
 * 
 *         Por ser una prueba Unitaria, se mockea el contexto de spring.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SucursalControllerTest {

	private static Long id = 1l;
	private static String direccion = "Avenida Eva Perón 3045 San Jose - Temperley, B1834 Lomas de Zamora, Provincia de Buenos Aires";
	private static Double latitud = -34.759204748953486;
	private static Double longitud = -58.35498419042968;
	private static Double latitudCliente = -34.85595468549554;
	private static Double longitudCliente = -58.16678864880442;
	private SucursalDto respuestaSucursalDto;
	private Sucursal nuevaSucursal;

	@InjectMocks
	SucursalController sucursalController;

	@Mock
	SucursalServiceImp sucursalServiceMock;
	
	@Mock
	private SucursalRepository sucursalRepositoryMock;

	@BeforeEach
	void setUp() {
		SucursalDto sucursalDtoMock = new SucursalDto(id, direccion, latitud, longitud);
		nuevaSucursal = new Sucursal(direccion, latitud, longitud);
		respuestaSucursalDto = new SucursalDto();

		Mockito.when(sucursalServiceMock.findById(id)).thenReturn(sucursalDtoMock);
		Mockito.when(sucursalServiceMock.save(nuevaSucursal)).thenReturn(sucursalDtoMock);
		Mockito.when(sucursalServiceMock.getNearbySucursal(new GeographicCoordinate(latitudCliente, longitudCliente)))
				.thenReturn(sucursalDtoMock);
	}

	@Test
	void getById() {

		respuestaSucursalDto = sucursalController.getById(id);
		Assertions.assertEquals(id, respuestaSucursalDto.getId());
		Assertions.assertEquals(direccion, respuestaSucursalDto.getDireccion());
		Assertions.assertEquals(latitud, respuestaSucursalDto.getLatitud());
		Assertions.assertEquals(longitud, respuestaSucursalDto.getLongitud());
	}

	@Test
	void create() {
		respuestaSucursalDto = sucursalController.create(nuevaSucursal, null);
		Assertions.assertEquals(direccion, respuestaSucursalDto.getDireccion());
		Assertions.assertEquals(latitud, respuestaSucursalDto.getLatitud());
		Assertions.assertEquals(longitud, respuestaSucursalDto.getLongitud());
	}

}
