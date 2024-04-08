package com.coderhouse.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.entidades.Productos;
import com.coderhouse.repositorios.ProductosRepository;

@Service
public class ProductosService {

	@Autowired
	private ProductosRepository productosRepository;
	
	public List<Productos> listarProductos() {
		return productosRepository.findAll();
	}
	
	public Productos agregarProducto(Productos producto) {
		return productosRepository.save(producto);
	}
	
	public boolean eliminarProductoPorId(int id) {
		try {
			productosRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
	public Productos mostrarProductoPorId(int id) {
		return productosRepository.findById(id).orElse(null);
	}
	
	public Productos editarPrecioProducto(int id, Productos productoActualizado) {
		try {
			Productos producto = productosRepository.findById(id).orElse(null);
			if (producto != null) {
				producto.setPrecio(productoActualizado.getPrecio());
				return productosRepository.save(producto);
			} else {
				return null;
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
}
