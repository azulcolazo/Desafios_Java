package com.coderhouse.servicios;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.entidades.Cliente;
import com.coderhouse.repositorios.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}
	
	public int edadCliente(int id) {
			Cliente cliente = clienteRepository.findById(id).orElse(null);
			if (cliente != null) {
				Period periodo = Period.between(cliente.getFechaNacimiento(), LocalDate.now());
				Integer edad = periodo.getYears();
				return edad;
			} else {
				return -1;
			}
		
	}
	
	public Cliente agregarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente editarNombreCliente(int id, Cliente clienteActualizado) {
		try {
			Cliente cliente = clienteRepository.findById(id).orElse(null);
			if (cliente != null) {
				cliente.setNombre(clienteActualizado.getNombre());
				cliente.setApellido(clienteActualizado.getApellido());
				return clienteRepository.save(cliente);
			} else {
				return null;
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public boolean eliminarClientePorId(int id) {
		try {
			clienteRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
	public Cliente mostrarClientePorId(int id) {
		return clienteRepository.findById(id).orElse(null);
	}

	
}
