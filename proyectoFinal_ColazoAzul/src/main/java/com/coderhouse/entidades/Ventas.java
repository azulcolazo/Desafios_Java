package com.coderhouse.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Ventas {
	
	@Id
	@Column(name = "id_venta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVenta;
	@Column(name = "cantidad")
	private int cantidad;
	@Column(name = "fecha")
	private LocalDate fecha;
	@Column(name = "importe_final")
	private float importeFinal;
	
	//Foreign key
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Clientes cliente;
	
	@Column(name = "cliente")
	private int idCliente;
	
	
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Productos producto;
	
	@Column(name = "producto")
	private int idProducto;
	
	//Constructor
	public Ventas() {
	}

	
	//Getters and Setters
	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public float getImporteFinal() {
		return importeFinal;
	}

	public void setImporteFinal(float importeFinal) {
		this.importeFinal = importeFinal;
	}
	
	
}
