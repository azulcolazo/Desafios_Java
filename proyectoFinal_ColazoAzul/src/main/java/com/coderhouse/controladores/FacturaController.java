package com.coderhouse.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.entidades.Factura;
import com.coderhouse.servicios.FacturaService;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

	@Autowired
	private FacturaService facturaService;
	
	// Solicitud GET, metodo para listar todas las facturas de la base de datos
	@GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Factura>> listarFacturas() {
		try {
			List<Factura> factura = facturaService.listarFacturas();
			return new ResponseEntity<>(factura, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Solicitud GET, metodo para listar una factura especifica proporcionando en id en la URL
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Factura> mostrarFacturaPorId(@PathVariable("id") int id) {
		try {
			Factura factura = facturaService.mostrarFacturaPorId(id);
			return new ResponseEntity<>(factura, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Solicitud POST, metodo para agregar una factura, proporcionando los datos de éste a través del formato JSON
	@PostMapping(value = "/agregar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> agregarFactura(@RequestBody Factura factura) {
		try {
			Object facturaGuardada = facturaService.agregarFactura(factura);
			if ( !(facturaGuardada instanceof String)) {
				return new ResponseEntity<>(facturaGuardada, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(facturaGuardada, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Solicitud DELETE, metodo para borrar una factura a partir de un id
	@DeleteMapping(value = "/{id}/eliminar")
	public ResponseEntity<Void> eliminarFacturaPorId(@PathVariable("id") int id) {
		try {
			boolean eliminado = facturaService.eliminarFacturaPorId(id);
			if (eliminado) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
