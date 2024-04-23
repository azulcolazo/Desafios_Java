package com.coderhouse.entidades;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente{

	//Campos-Atributos
	@Id
	@Column(name = "id_cliente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCliente;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellido")
	private String apellido;
	@Column(name = "dni")
	private int dni;
	@Column (name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	//Foreign key
	@OneToMany(mappedBy = "cliente")
	private List<Factura> factura;
	
	//Constructor
	public Cliente(int idCliente, String nombre, String apellido, int dni, LocalDate fechaNacimiento, List<Venta> venta,
			List<Factura> factura) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.factura = factura;
	}

	public Cliente() {
		super();
	}



	//Getters and Setters
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/*public List<Factura> getFacturas() {
		return factura;
	}

	public void setFacturas(List<Factura> factura) {
		this.factura = factura;
	}*/


	//Hashcode and Equals para dni
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return dni == other.dni;
	}
	
	
}
