package com.coderhouse.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.entidades.Ventas;
import com.coderhouse.repositorios.VentasRepository;

@Service
public class VentasService {

	@Autowired
	private VentasRepository ventasRepository;
	
	public List<Ventas> listarVentas() {
		return ventasRepository.findAll();
	}
	
	public Ventas mostrarVentaPorId(int id) {
		return ventasRepository.findById(id).orElse(null);
	}
	
	public Ventas agregarVenta(Ventas venta) {
		return ventasRepository.save(venta);
	}
	
	public boolean eliminarVentaPorId(int id) {
		try {
			ventasRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
}
