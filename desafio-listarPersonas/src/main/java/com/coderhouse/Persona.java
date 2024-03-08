package com.coderhouse;

public class Persona {
	
	// Atributos - Variables de instancia
	
	private String nombre;
	private String apellido;
	
	// Constructores
	
	public Persona() {
		super();
	}
	public Persona(String nombre, String apellido) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	// Getters and Setters
	
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return this.apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	// toString
	
	@Override
	public String toString() {
		return "Persona [nombre = " + nombre + ", apellido = " + apellido + "]";
	}
	
	
	
}
