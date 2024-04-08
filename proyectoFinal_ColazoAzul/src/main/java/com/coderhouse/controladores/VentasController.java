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

import com.coderhouse.entidades.Ventas;
import com.coderhouse.servicios.VentasService;

@RestController
@RequestMapping("/ventas")
public class VentasController {

	@Autowired
	private VentasService ventasService;
	
	@GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Ventas>> listarVentas() {
		try {
			List<Ventas> ventas = ventasService.listarVentas();
			return new ResponseEntity<>(ventas, HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Ventas> mostrarVentaPorId(@PathVariable("id") int id) {
		try {
			Ventas venta = ventasService.mostrarVentaPorId(id);
			if (venta != null) {
				return new ResponseEntity<>(venta, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/agregar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Ventas> agregarVenta(@RequestBody Ventas venta) {
		try {
			Ventas ventaAgregada = ventasService.agregarVenta(venta);
			return new ResponseEntity<>(ventaAgregada, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}/eliminar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> eliminarVentaPorId(@PathVariable("id") int id) {
		try {
			boolean eliminado = ventasService.eliminarVentaPorId(id);
			if (eliminado) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
