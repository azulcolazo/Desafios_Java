package com.coderhouse.entidades;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ventas")
public class Ventas {
	
	@Id
	@Column(name = "id_venta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVenta;
	@Column(name = "fecha")
	private LocalDate fecha;
	@Column(name = "cantidad")
	private List<Integer> cantidad;
	
	
	//Foreign key
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Clientes cliente;

	
	@ManyToMany
	@JoinTable(name = "pedidos", joinColumns = @JoinColumn(name = "id_venta"), inverseJoinColumns = @JoinColumn(name = "id_producto)"))
	private List<Productos> productos;
	
	/*@ManyToMany(mappedBy = "ventas")
	private List<Productos> productos;*/
	
	
	//Constructor
	public Ventas() {
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


	public Clientes getCliente() {
		return cliente;
	}


	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}


	public List<Integer> getCantidad() {
		return cantidad;
	}


	public void setCantidad(List<Integer> cantidad) {
		this.cantidad = cantidad;
	}


	
	
}
