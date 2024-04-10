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
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Factura>> listarFacturas() {
		try {
			List<Factura> factura = facturaService.listarFacturas();
			return new ResponseEntity<>(factura, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Factura> mostrarFacturaPorId(@PathVariable("id") int id) {
		try {
			Factura factura = facturaService.mostrarFacturaPorId(id);
			return new ResponseEntity<>(factura, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/{id}/editar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Factura> editarFacturaPorId(@PathVariable("id") int id, @RequestBody Factura factura) {
		try {
			Factura facturaEditada = facturaService.editarFacturaPorId(factura, id);
			if (facturaEditada != null) {
				return new ResponseEntity<>(facturaEditada, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/{id}/agregar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Factura> agregarFactura(@PathVariable("id") int id, @RequestBody Factura factura) {
		try {
			Factura facturaGuardada = facturaService.agregarFactura(factura);
			if (facturaGuardada != null) {
				return new ResponseEntity<>(facturaGuardada, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}/eliminar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> eliminarFacturaPorId(@PathVariable("id") int id) {
		try {
			boolean eliminado = facturaService.eliminarClientePorId(id);
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
