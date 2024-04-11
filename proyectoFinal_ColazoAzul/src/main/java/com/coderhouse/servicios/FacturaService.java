package com.coderhouse.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.entidades.Factura;
import com.coderhouse.repositorios.FacturaRepository;

@Service
public class FacturaService {

	@Autowired
	private FacturaRepository facturaRepository;
	
	public List<Factura> listarFacturas() {
		return facturaRepository.findAll();
	}
	
	public Factura mostrarFacturaPorId(int id) {
		return facturaRepository.findById(id).orElse(null);
	}
	
	public boolean eliminarFacturaPorId(int id) {
		try {
			facturaRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
	public Factura agregarFactura(Factura factura) {
        return facturaRepository.save(factura);
	}
	
	public Factura editarFacturaPorId(Factura factura, int id) {
		try {
			Factura facturaAEditar = facturaRepository.findById(id).orElse(null);
			if (facturaAEditar != null) {
				facturaAEditar.setMetodoPago(factura.getMetodoPago());
				return facturaRepository.save(facturaAEditar);
			} else {
				return null;
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
