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

import com.coderhouse.entidades.Cliente;
import com.coderhouse.entidades.Venta;
import com.coderhouse.servicios.VentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
	private VentaService ventaService;
	
	// Solicitud GET, metodo para listar todas las ventas de la base de datos
	@GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Venta>> listarVentas() {
		try {
			List<Venta> venta = ventaService.listarVentas();
			return new ResponseEntity<>(venta, HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Solicitud GET, metodo para listar una venta especifica proporcionando en id en la URL
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
	
	// Solicitud POST, metodo para agregar una venta, proporcionando los datos de éste a través del formato JSON
	@PostMapping(value = "/agregar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Venta> agregarVenta(@RequestBody Venta venta) {
		try {
			Venta ventaAgregada = ventaService.agregarVenta(venta);
			return new ResponseEntity<>(ventaAgregada, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Solicitud DELETE, metodo para borrar una venta a partir de un id
	@DeleteMapping(value = "/{id}/eliminar")
	public ResponseEntity<?> eliminarVentaPorId(@PathVariable("id") int id) {
		try {
			boolean eliminado = ventaService.eliminarVentaPorId(id);
			if (eliminado) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Solicitud PUT, metodo para agregar la factura correspondiente a una venta a través del ID proporcionado en la URL
	@PutMapping(value = "{id}/editar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Venta> editarFacturaVenta(@PathVariable("id") int id, @RequestBody Venta ventaActualizada) {
		try {
			Venta ventaAEditar = ventaService.agregarFactura(id, ventaActualizada);
			if (ventaAEditar != null) {
				return new ResponseEntity<Venta>(ventaAEditar, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
}
