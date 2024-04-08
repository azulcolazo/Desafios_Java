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

import com.coderhouse.entidades.Productos;
import com.coderhouse.servicios.ProductosService;

public class ProductosController {

	@Autowired
	private ProductosService productosService;
	
	@GetMapping(value= "/", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Productos>> listarProductos() {
		try {
			List<Productos> productos = productosService.listarProductos();
			return new ResponseEntity<>(productos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/agregar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Productos> agregarProducto(@RequestBody Productos producto) {
		try {
			Productos productoAgregado = productosService.agregarProducto(producto);
			return new ResponseEntity<>(productoAgregado, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}/eliminar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> eliminarProductoPorId(@PathVariable("id") int id) {
		try {
			boolean productoEliminado = productosService.eliminarProductoPorId(id);
			if (productoEliminado) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Productos> mostrarProductoPorId(@PathVariable("id") int id) {
		try {
			Productos producto = productosService.mostrarProductoPorId(id);
			if (producto != null) {
				return new ResponseEntity<>(producto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/{id}/editar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Productos> editarPrecioProducto(@PathVariable("id") int id, @RequestBody Productos producto) {
		try {
			Productos productoActualizado = productosService.editarPrecioProducto(id, producto);
			if (productoActualizado != null) {
				return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
