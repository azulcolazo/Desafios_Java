package com.coderhouse.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coderhouse.clasesSoporte.Linea;
import com.coderhouse.entidades.Cliente;
import com.coderhouse.entidades.Factura;
import com.coderhouse.entidades.Producto;
import com.coderhouse.repositorios.ClienteRepository;
import com.coderhouse.repositorios.FacturaRepository;
import com.coderhouse.repositorios.ProductoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FacturaService {

	@Autowired
	private FacturaRepository facturaRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ProductoRepository productoRepository;
	
	private final String FECHA_URL = "http://worldtimeapi.org/api/timezone/America/Argentina/Cordoba";
	private RestTemplate restTemplate;
	
	
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
	
	public Object agregarFactura(Factura factura) {
		//Validacion cliente existente
		Cliente cliente = clienteRepository.findById(factura.getCliente().getIdCliente()).orElse(null);
		if(cliente == null) {
			return "Cliente no encontrado";
		}
		
		List<Linea> lineas = factura.getLineas();
		for(Linea linea : lineas) {
			//Validacion producto existente
			Producto producto = linea.getProducto();
			Producto existeProducto = productoRepository.findById(producto.getIdProducto()).orElse(null);
			if(existeProducto == null) {
				return "No existe un producto";
			}
			//Validacion de stock suficiente	
			if(producto.getStock() < linea.getCantidad()) {
				return "No stock suficiente";
			} else {
				int stockActualizado = producto.getStock() - linea.getCantidad();
				producto.setStock(stockActualizado);
			}
		}
		//Calculo de monto final
		float monto = factura.calcularMontoFinal();
		factura.setMontoFinal(monto);
		
		//Seteo de fecha a través de una API Rest externa
		LocalDate fecha;
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(FECHA_URL, String.class);
			
			ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode nodoRespuesta = objectMapper.readTree(response.getBody());
	        String fechaHora = nodoRespuesta.get("datetime").asText();
	        fecha = LocalDate.parse(fechaHora.substring(0, 10));
		} catch (Exception e) {
			fecha = LocalDate.now();
		}
		factura.setFecha(fecha);
		factura.setProcesada(true);
		facturaRepository.save(factura);
		
		return factura;
	}
	

}
