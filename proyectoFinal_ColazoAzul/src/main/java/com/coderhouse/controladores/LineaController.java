package com.coderhouse.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.clasesSoporte.Linea;
import com.coderhouse.servicios.LineaService;

@RestController
@RequestMapping("/lineas")
public class LineaController {
	
	@Autowired
	private LineaService lineaService;
	
	@GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Linea>> listarLineas() {
		try {
			List<Linea> lineas = lineaService.listarLineas();
			return new ResponseEntity<>(lineas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/agregar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> agregarLinea(@RequestBody Linea linea) {
		try {
			Object lineaAgregada = lineaService.agregarLinea(linea);
			if (lineaAgregada instanceof Linea) {
				return new ResponseEntity<>(lineaAgregada, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(lineaAgregada, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<?> eliminarLinea(@PathVariable("id") int id) {
		try {
			Object lineaEliminada = lineaService.eliminarLinea(id);
			if (lineaEliminada instanceof String) {
				return new ResponseEntity<>(lineaEliminada, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
