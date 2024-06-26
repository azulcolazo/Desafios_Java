package com.coderhouse.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.entidades.Factura;
import com.coderhouse.entidades.Venta;
import com.coderhouse.repositorios.VentaRepository;

@Service
public class VentaService {

	@Autowired
	private VentaRepository ventaRepository;
	
	public List<Venta> listarVentas() {
		return ventaRepository.findAll();
	}
	
	public Venta mostrarVentaPorId(int id) {
		return ventaRepository.findById(id).orElse(null);
	}
	
	public Venta agregarVenta(Venta venta) {
		return ventaRepository.save(venta);
	}
	
	public boolean eliminarVentaPorId(int id) {
		try {
			ventaRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
	public Venta agregarFactura(int id, Venta ventaActualizada) {
		try {
			Venta venta = ventaRepository.findById(id).orElse(null);
			if(venta != null) {
				venta.setFactura(ventaActualizada.getFactura());
				return ventaRepository.save(venta);
			} else {
				return null;
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
