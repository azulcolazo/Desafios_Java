package com.coderhouse.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.entidades.Facturas;
import com.coderhouse.repositorios.FacturasRepository;

@Service
public class FacturasService {

	@Autowired
	private FacturasRepository facturasRepository;
	
	public List<Facturas> listarFacturas() {
		return facturasRepository.findAll();
	}
	
	public Facturas mostrarFacturaPorId(int id) {
		return facturasRepository.findById(id).orElse(null);
	}
	
	public boolean eliminarClientePorId(int id) {
		try {
			facturasRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
	public Facturas agregarFactura(Facturas factura) {
		return facturasRepository.save(factura);
	}
	
	public Facturas editarFacturaPorId(Facturas factura, int id) {
		try {
			Facturas facturaAEditar = facturasRepository.findById(id).orElse(null);
			if (facturaAEditar != null) {
				facturaAEditar.setMetodoPago(factura.getMetodoPago());
				return facturasRepository.save(facturaAEditar);
			} else {
				return null;
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
