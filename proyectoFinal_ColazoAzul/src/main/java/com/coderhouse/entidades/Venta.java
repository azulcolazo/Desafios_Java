package com.coderhouse.entidades;

import java.time.LocalDate;
import java.util.List;

import com.coderhouse.clasesSoporte.Linea;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "venta")
public class Venta {
	
	//Atributos-Campos
	@Id
	@Column(name = "id_venta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVenta;
	@Column(name = "fecha")
	private LocalDate fecha;
	
	
	@OneToOne
	@JoinColumn(name = "factura")
	private Factura factura;
	

	//Constructor
	public Venta(int idVenta, LocalDate fecha, List<Linea> lineas, Cliente cliente, Factura factura) {
		super();
		this.idVenta = idVenta;
		this.fecha = fecha;
		this.factura = factura;
	}
	
	public Venta() {
		super();
	}


	//Getters y Setters
	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
}
