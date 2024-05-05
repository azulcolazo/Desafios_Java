package com.coderhouse.clasesSoporte;

import com.coderhouse.entidades.Factura;
import com.coderhouse.entidades.Producto;
import com.coderhouse.entidades.Venta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "linea")
public class Linea {
	
	//Campos - Atrinutos

	@Id
	@Column(name = "id_linea")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLinea;
	@Column(name = "cantidad")
	private int cantidad;
	
	//Relaciones entre tablas
	
	@ManyToOne
	@JoinColumn(name = "producto")
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "factura")
	private Factura factura;

	//Constructor
	
	public Linea(int idLinea, int cantidad, Producto producto, Factura factura, Venta venta) {
		super();
		this.idLinea = idLinea;
		this.cantidad = cantidad;
		this.producto = producto;
		this.factura = factura;
		//this.venta = venta;
	}

	public Linea() {
		super();
	}

	//Getters y setters
	
	public int getIdLinea() {
		return idLinea;
	}

	public void setIdLinea(int idLinea) {
		this.idLinea = idLinea;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	
}
