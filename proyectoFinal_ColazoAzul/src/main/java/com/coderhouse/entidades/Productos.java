package com.coderhouse.entidades;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Productos {

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
	
	//Foreign key
	/*@ManyToMany(mappedBy = "producto")
	private List<Ventas> ventas;*/
	
	@ManyToMany
	@JoinTable(name = "pedidos", joinColumns = @JoinColumn(name = "id_producto"), inverseJoinColumns = @JoinColumn(name = "id_venta"))
	private List<Ventas> ventas;
	
	//Constructor
	public Productos() {
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
	
	

} 
