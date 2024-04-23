package com.coderhouse.entidades;

import java.util.List;

import com.coderhouse.clasesSoporte.Linea;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

	//Campos-Atributos
	@Id
	@Column(name = "id_producto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "categoria")
	private String categoria;
	@Column(name = "precio")
	private float precio;
	@Column(name = "stock")
	private int stock;

	
	@OneToMany(mappedBy = "producto")
	private List<Linea> lineas;
	
	
	
	//Constructor
	public Producto(int idProducto, String nombre, String categoria, float precio, int stock, List<Venta> venta) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
		//this.ventas = venta;
	}
	
	public Producto() {
		super();
	}


	//Getters and Setters
	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	/*public List<Linea> getLineas() {
		return lineas;
	}

	public void setLineas(List<Linea> lineas) {
		this.lineas = lineas;
	}*/
	
	
	
	
	

} 
