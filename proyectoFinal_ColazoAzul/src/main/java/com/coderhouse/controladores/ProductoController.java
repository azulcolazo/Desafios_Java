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

import com.coderhouse.entidades.Producto;
import com.coderhouse.servicios.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	// Solicitud GET, metodo para listar todos los productos de la base de datos
	@GetMapping(value= "/", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Producto>> listarProductos() {
		try {
			List<Producto> producto = productoService.listarProductos();
			return new ResponseEntity<>(producto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Solicitud POST, metodo para agregar un producto, proporcionando los datos de éste a través del formato JSON
	@PostMapping(value = "/agregar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
		try {
			Producto productoAgregado = productoService.agregarProducto(producto);
			return new ResponseEntity<>(productoAgregado, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Solicitud DELETE, metodo para borrar un producto a partir de un id
	@DeleteMapping(value = "/{id}/eliminar")
	public ResponseEntity<Void> eliminarProductoPorId(@PathVariable("id") int id) {
		try {
			boolean productoEliminado = productoService.eliminarProductoPorId(id);
			if (productoEliminado) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Solicitud GET, metodo para listar un producto especifico proporcionando en id en la URL
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Producto> mostrarProductoPorId(@PathVariable("id") int id) {
		try {
			Producto producto = productoService.mostrarProductoPorId(id);
			if (producto != null) {
				return new ResponseEntity<>(producto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Solicitud PUT, metodo para editar el precio de un producto a través del ID proporcionado en la URL
	@PutMapping(value = "/{id}/editar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Producto> editarPrecioProducto(@PathVariable("id") int id, @RequestBody Producto producto) {
		try {
			Producto productoActualizado = productoService.editarPrecioProducto(id, producto);
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
