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

import com.coderhouse.entidades.Facturas;
import com.coderhouse.servicios.FacturasService;

@RestController
@RequestMapping("/facturas")
public class FacturasController {

	@Autowired
	private FacturasService facturasService;
	
	@GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Facturas>> listarFacturas() {
		try {
			List<Facturas> facturas = facturasService.listarFacturas();
			return new ResponseEntity<>(facturas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Facturas> mostrarFacturaPorId(@PathVariable("id") int id) {
		try {
			Facturas factura = facturasService.mostrarFacturaPorId(id);
			return new ResponseEntity<>(factura, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/{id}/editar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Facturas> editarFacturaPorId(@PathVariable("id") int id, @RequestBody Facturas factura) {
		try {
			Facturas facturaEditada = facturasService.editarFacturaPorId(factura, id);
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
	public ResponseEntity<Facturas> agregarFactura(@PathVariable("id") int id, @RequestBody Facturas factura) {
		try {
			Facturas facturaGuardada = facturasService.agregarFactura(factura);
			return new ResponseEntity<>(facturaGuardada, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}/eliminar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> eliminarFacturaPorId(@PathVariable("id") int id) {
		try {
			boolean eliminado = facturasService.eliminarClientePorId(id);
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
