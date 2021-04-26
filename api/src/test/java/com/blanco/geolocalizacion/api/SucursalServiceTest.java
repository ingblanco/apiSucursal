package com.blanco.geolocalizacion.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.blanco.geolocalizacion.api.dto.SucursalDto;
import com.blanco.geolocalizacion.api.model.GeographicCoordinate;
import com.blanco.geolocalizacion.api.model.Sucursal;
import com.blanco.geolocalizacion.api.repository.SucursalRepository;
import com.blanco.geolocalizacion.api.service.imp.GeolocalizacionServiceImp;
import com.blanco.geolocalizacion.api.service.imp.SucursalServiceImp;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SucursalServiceTest {

	private static String DIRECCION_SUCURSAL_CERCANA = "C. 12 1249, B1900 La Plata, Provincia de Buenos Aires";
	private static Double LATITUD_CLIENTE = -32.95650816650679;
	private static Double LONGITUD_CLIENTE = -60.689801712628984;
	private static Double LATITUD_SUCURSAL_CERCANA = -34.913857500000006;
	private static Double LONGITUD_SUCURSAL_CERCANA = -57.94894639999999;
	private static Double LATITUD_SUCURSAL_BARILOCHE_RIO_NEGRO = -41.13704412268097;
	private static Double LONGITUD_SUCURSAL_BARILOCHE_RIO_NEGRO = -71.29740282950365;
	private static Double LATITUD_SUCURSAL_COMODORO_RIVADAVIA = -46.42867694497696;
	private static Double LONGITUD_SUCURSAL_COMODORO_RIVADAVIA = -67.48743202874859;
	private static String DIRECCION_SUCURSAL_BARILOCHE_RIO_NEGRO = "Clemente, Onelli 407, San Carlos de Bariloche, Río Negro";
	private static String DIRECCION_SUCURSAL_COMODORO_RIVADAVIA_CHUBUT = "San Martín 341, U9000 Comodoro Rivadavia, Chubut";
	private GeographicCoordinate posicionCliente;
	private List<Sucursal> sucursales;
	private Sucursal sucursalCercana;


	@InjectMocks
	private SucursalServiceImp sucursalService;

	@Mock
	private SucursalRepository sucursalRepositoryMock;

	@Spy
	private GeolocalizacionServiceImp geolocalizacionServiceMock;
	

	@BeforeEach
	void setUp() {

		// Posicion del cliente : Rosario, Santa Fe
		posicionCliente = new GeographicCoordinate(LATITUD_CLIENTE, LONGITUD_CLIENTE);
		// Sucursal mas cercana a la posicion del cliente
		sucursalCercana = new Sucursal(1l, DIRECCION_SUCURSAL_CERCANA, LATITUD_SUCURSAL_CERCANA,
				LONGITUD_SUCURSAL_CERCANA);

		// Sucursales que devolveria SucursalRepository.findAll()
		sucursales = new ArrayList<Sucursal>();

		sucursales.add(new Sucursal(2l, DIRECCION_SUCURSAL_BARILOCHE_RIO_NEGRO, LATITUD_SUCURSAL_BARILOCHE_RIO_NEGRO,
				LONGITUD_SUCURSAL_BARILOCHE_RIO_NEGRO));
		sucursales.add(new Sucursal(3l, DIRECCION_SUCURSAL_COMODORO_RIVADAVIA_CHUBUT, LATITUD_SUCURSAL_COMODORO_RIVADAVIA,
				LONGITUD_SUCURSAL_COMODORO_RIVADAVIA));
		sucursales.add(sucursalCercana);

		Mockito.when(sucursalRepositoryMock.findAll()).thenReturn(sucursales);
		Mockito.when(sucursalRepositoryMock.save(sucursalCercana)).thenReturn(sucursalCercana);
		
	}

	@Test
	void getSucursalCercana() {
		SucursalDto sucursalCercanaDto = sucursalService.getNearbySucursal(posicionCliente);
		Assertions.assertEquals(sucursalCercanaDto.getId(), sucursalCercana.getId());
		Assertions.assertEquals(sucursalCercanaDto.getLatitud(), sucursalCercana.getLatitud());
		Assertions.assertEquals(sucursalCercanaDto.getLongitud(), sucursalCercana.getLongitud());
	}
	
	@Test
	void create() {
		SucursalDto  nuevaSucursal = sucursalService.save(sucursalCercana);
		Assertions.assertNotNull(nuevaSucursal);
		Assertions.assertNotNull(nuevaSucursal.getId());
	}
	
}
