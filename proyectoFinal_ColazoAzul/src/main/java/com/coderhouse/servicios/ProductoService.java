package com.coderhouse.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.entidades.Producto;
import com.coderhouse.repositorios.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	
	public List<Producto> listarProductos() {
		return productoRepository.findAll();
	}
	
	public Producto agregarProducto(Producto producto) {
		return productoRepository.save(producto);
	}
	
	public boolean eliminarProductoPorId(int id) {
		try {
			productoRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
	public Producto mostrarProductoPorId(int id) {
		return productoRepository.findById(id).orElse(null);
	}
	
	public Producto editarPrecioProducto(int id, Producto productoActualizado) {
		try {
			Producto producto = productoRepository.findById(id).orElse(null);
			if (producto != null) {
				producto.setPrecio(productoActualizado.getPrecio());
				return productoRepository.save(producto);
			} else {
				return null;
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
}
