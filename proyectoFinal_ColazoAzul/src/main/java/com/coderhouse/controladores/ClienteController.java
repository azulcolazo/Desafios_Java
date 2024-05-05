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
import com.coderhouse.servicios.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	// Solicitud GET, metodo para listar todos los clientes de la base de datos
	@GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Cliente>> listarClientes() {
		try {
			List<Cliente> clientes = clienteService.listarClientes();
			return new ResponseEntity<>(clientes, HttpStatus.OK); 

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}

	} 
	
	// Solicitud GET, metodo para calcular la edad del cliente, identificado por el id proporcionado en la URL, a partir de su fecha de nacimiento
	@GetMapping(value = "/{id}/edad", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Integer> edadCliente(@PathVariable("id") Integer id) {
		try {
			int edad = clienteService.edadCliente(id);
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
	public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente) {
		try {
			Cliente clienteAgregado = clienteService.agregarCliente(cliente);
			return new ResponseEntity<>(clienteAgregado, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// Solicitud PUT, metodo para editar el nombre y apellido de un cliente a través del ID proporcionado en la URL
	@PutMapping(value = "/{id}/editar/", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Cliente> editarNombreCliente(@PathVariable("id") int id, @RequestBody Cliente clienteActualizado) {
		try {
			Cliente clienteAEditar = clienteService.editarNombreCliente(id, clienteActualizado);
			if (clienteAEditar != null) {
				return new ResponseEntity<Cliente>(clienteAEditar, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	
	// Solicitud DELETE, metodo para borrar un cliente a partir de un id
	@DeleteMapping(value = "{id}/eliminar")
	public ResponseEntity<Void> eliminarClientePorId (@PathVariable("id") int id) {
		try {
			boolean clienteABorrar = clienteService.eliminarClientePorId(id);
			if (clienteABorrar) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//Solicitud GET, metodo para listar un cliente especifico proporcionando en id en la URL
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Cliente> mostrarClientPorId(@PathVariable("id") int id) {
		try {
			Cliente cliente = clienteService.mostrarClientePorId(id);
			if (cliente != null) {
				return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
