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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ventas")
public class Venta {
	
	//Atributos-Campos
	@Id
	@Column(name = "id_venta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVenta;
	@Column(name = "fecha")
	private LocalDate fecha;
	@Column(name = "cantidad")
	private List<Integer> cantidad;
	
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "venta")
	private List<Factura> factura;
	
	@ManyToMany
    @JoinTable(name = "venta_producto",
               joinColumns = @JoinColumn(name = "id_venta"),
               inverseJoinColumns = @JoinColumn(name = "id_producto"))
    private List<Producto> producto;
	
	
	//Constructor
	public Venta() {
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Integer> getCantidad() {
		return cantidad;
	}

	public void setCantidad(List<Integer> cantidad) {
		this.cantidad = cantidad;
	}

	public List<Factura> getFacturas() {
		return factura;
	}

	public void setFacturas(List<Factura> factura) {
		this.factura = factura;
	}

	public List<Producto> getProductos() {
		return producto;
	}

	public void setProductos(List<Producto> producto) {
		this.producto = producto;
	}

	
}
