package com.coderhouse.controladores;

import java.time.LocalDate;
import java.time.Period;
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
import com.coderhouse.repositorios.ClientesRepository;

@RestController
@RequestMapping("/clientes")
public class ClientesController {
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	// Solicitud GET, metodo para listar todos los clientes de la base de datos
	@GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Clientes>> listarClientes() {
		try {
			List<Clientes> clientes = clientesRepository.findAll();
			return new ResponseEntity<>(clientes, HttpStatus.OK); 

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}

	} 
	
	// Desafio de clase 11
	// Solicitud GET, metodo para calcular la edad del cliente, identificado por el id proporcionado en la URL, a partir de su fecha de nacimiento
	@GetMapping(value = "/{id}/edad", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Integer> edadCliente(@PathVariable("id") Integer id) {
		try {
			Clientes cliente = clientesRepository.findById(id).orElse(null);
			if (cliente != null) {
				Period periodo = Period.between(cliente.getFechaNacimiento(), LocalDate.now());
				Integer edad = periodo.getYears();
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
		clientesRepository.save(cliente);
		return new ResponseEntity<>(cliente, HttpStatus.CREATED);
	}
	
	// Solicitud PUT, metodo para editar el nombre y apellido de un cliente a través del ID proporcionado en la URL
	@PutMapping(value = "/{id}/editar/", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Clientes> editarNombreCliente(@PathVariable("id") Integer id, @RequestBody Clientes clienteActualizado) {
		try {
			Clientes clienteAEditar = clientesRepository.findById(id).orElse(null);
			if (clienteAEditar != null) {
				clienteAEditar.setNombre(clienteActualizado.getNombre());
				clienteAEditar.setApellido(clienteActualizado.getApellido());
				clientesRepository.save(clienteAEditar);
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
	public ResponseEntity<Void> borrarCliente (@PathVariable("id") Integer id) {
		try {
			Clientes clienteABorrar = clientesRepository.findById(id).orElse(null);
			if (clienteABorrar != null) {
				clientesRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
