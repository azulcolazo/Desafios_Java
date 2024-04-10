package com.coderhouse.servicios;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.entidades.Clientes;
import com.coderhouse.repositorios.ClientesRepository;

@Service
public class ClientesService {
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	public List<Clientes> listarClientes() {
		return clientesRepository.findAll();
	}
	
	public int edadCliente(int id) {
			Clientes cliente = clientesRepository.findById(id).orElse(null);
			if (cliente != null) {
				Period periodo = Period.between(cliente.getFechaNacimiento(), LocalDate.now());
				Integer edad = periodo.getYears();
				return edad;
			} else {
				return -1;
			}
		
	}
	
	public Clientes agregarCliente(Clientes cliente) {
		return clientesRepository.save(cliente);
	}
	
	public Clientes editarNombreCliente(int id, Clientes clienteActualizado) {
		try {
			Clientes cliente = clientesRepository.findById(id).orElse(null);
			if (cliente != null) {
				cliente.setNombre(clienteActualizado.getNombre());
				cliente.setApellido(clienteActualizado.getApellido());
				return clientesRepository.save(cliente);
			} else {
				return null;
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public boolean eliminarClientePorId(int id) {
		try {
			clientesRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
	public Clientes mostrarClientePorId(int id) {
		return clientesRepository.findById(id).orElse(null);
	}

	
}
