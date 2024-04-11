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

import com.coderhouse.entidades.Venta;
import com.coderhouse.servicios.VentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
	private VentaService ventaService;
	
	@GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Venta>> listarVentas() {
		try {
			List<Venta> venta = ventaService.listarVentas();
			return new ResponseEntity<>(venta, HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Venta> mostrarVentaPorId(@PathVariable("id") int id) {
		try {
			Venta venta = ventaService.mostrarVentaPorId(id);
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
	public ResponseEntity<Venta> agregarVenta(@RequestBody Venta venta) {
		try {
			Venta ventaAgregada = ventaService.agregarVenta(venta);
			return new ResponseEntity<>(ventaAgregada, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}/eliminar")
	public ResponseEntity<Void> eliminarVentaPorId(@PathVariable("id") int id) {
		try {
			boolean eliminado = ventaService.eliminarVentaPorId(id);
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
