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

import com.coderhouse.entidades.Clientes;
import com.coderhouse.servicios.ClientesService;

@RestController
@RequestMapping("/clientes")
public class ClientesController {
	
	@Autowired
	private ClientesService clientesService;
	
	// Solicitud GET, metodo para listar todos los clientes de la base de datos
	@GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Clientes>> listarClientes() {
		try {
			List<Clientes> clientes = clientesService.listarClientes();
			return new ResponseEntity<>(clientes, HttpStatus.OK); 

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}

	} 
	
	// Solicitud GET, metodo para calcular la edad del cliente, identificado por el id proporcionado en la URL, a partir de su fecha de nacimiento
	@GetMapping(value = "/{id}/edad", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Integer> edadCliente(@PathVariable("id") Integer id) {
		try {
			int edad = clientesService.edadCliente(id);
			if (edad != -1) {
				return new ResponseEntity<>(edad, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Solicitud POST, metodo para agregar un cliente, proporcionando los datos de éste a través del formato JSON
	@PostMapping(value = "/agregar", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Clientes> agregarCliente(@RequestBody Clientes cliente) {
		try {
			Clientes clienteAgregado = clientesService.agregarCliente(cliente);
			return new ResponseEntity<>(clienteAgregado, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// Solicitud PUT, metodo para editar el nombre y apellido de un cliente a través del ID proporcionado en la URL
	@PutMapping(value = "/{id}/editar/", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Clientes> editarNombreCliente(@PathVariable("id") int id, @RequestBody Clientes clienteActualizado) {
		try {
			Clientes clienteAEditar = clientesService.editarNombreCliente(id, clienteActualizado);
			if (clienteAEditar != null) {
				return new ResponseEntity<Clientes>(clienteAEditar, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	
	// Solicitud DELETE, metodo para borrar un cliente a partir de un id
	@DeleteMapping(value = "{id}/borrar")
	public ResponseEntity<Void> borrarClientePorId (@PathVariable("id") int id) {
		try {
			boolean clienteABorrar = clientesService.eliminarClientePorId(id);
			if (clienteABorrar) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Clientes> mostrarClientPorId(@PathVariable("id") int id) {
		try {
			Clientes cliente = clientesService.mostrarClientePorId(id);
			if (cliente != null) {
				return new ResponseEntity<Clientes>(cliente, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
