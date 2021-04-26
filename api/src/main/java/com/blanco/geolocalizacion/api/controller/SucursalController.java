package com.blanco.geolocalizacion.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.blanco.geolocalizacion.api.dto.SucursalDto;
import com.blanco.geolocalizacion.api.error.ErrorResponse;
import com.blanco.geolocalizacion.api.exception.InvalidDataException;
import com.blanco.geolocalizacion.api.model.GeographicCoordinate;
import com.blanco.geolocalizacion.api.model.Sucursal;
import com.blanco.geolocalizacion.api.service.SucursalService;

import io.swagger.annotations.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/sucursal")
public class SucursalController {

	@Autowired
	private SucursalService sucursalService;

	@ApiOperation(value = "getById"
            ,notes = "Retorna la sucursal asociada al ID enviado por query param.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Se obtuvo correctamente el recurso solicitado", response = SucursalDto.class ),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping()
	public SucursalDto getById(@RequestParam() Long id) {

		return sucursalService.findById(id);

	}

	@ApiOperation(value = "Create"
            ,notes = "Crea una sucursal y la retorna.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se creo correctamente", response = SucursalDto.class ),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public SucursalDto create(@RequestBody @Valid Sucursal sucursal, BindingResult result) {

		if (result != null) {
			if (result.hasErrors()) {
				throw new InvalidDataException(result);
			}
		}
		return sucursalService.save(sucursal);
	}
	@ApiOperation(value = "getNearbySucursal"
            ,notes = "Retorna la sucursal mas cercana a la coordenada geografica recibida")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Se obtuvo correctamente el recurso solicitado", response = SucursalDto.class ),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/getNearbySucursal")
	public SucursalDto getNearbySucursal(@RequestParam Double latitud, @RequestParam Double longitud) {

		return sucursalService.getNearbySucursal(new GeographicCoordinate(latitud, longitud));
	}

}
