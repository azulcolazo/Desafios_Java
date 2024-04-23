package com.coderhouse.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.clasesSoporte.Linea;
import com.coderhouse.entidades.Producto;
import com.coderhouse.repositorios.LineaRepository;
import com.coderhouse.repositorios.ProductoRepository;

@Service
public class LineaService {

	@Autowired
	private LineaRepository lineaRepository;
	@Autowired
	private ProductoRepository productoRepository;
	
	public List<Linea> listarLineas() {
		return lineaRepository.findAll();
	}
	
	public Object agregarLinea(Linea linea) {
		Producto producto = linea.getProducto();
		Producto existeProducto = productoRepository.findById(producto.getIdProducto()).orElse(null);
		if(existeProducto == null) {
			return "No existe producto";
		}
		return lineaRepository.save(linea);
	}
	
	public Object eliminarLinea(int id) {
		Linea existeLinea = lineaRepository.findById(id).orElse(null);
		if (existeLinea != null) {
			try {
				lineaRepository.deleteById(id);
				return true;
			} catch (EmptyResultDataAccessException e) {
				return false;
			}
		} 
		return "No existe la linea";
	}
}
