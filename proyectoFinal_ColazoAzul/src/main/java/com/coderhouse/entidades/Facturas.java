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
@Table(name = "factura")
public class Facturas {

	@Id
	@Column(name = "id_factura")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFactura;
	@Column(name = "metodo_pago")
	private String metodoPago;
	@Column(name = "fecha")
	private LocalDate fecha;
	@Column(name = "monto_total")
	private float montoTotal;
	
	
	@ManyToOne
	@JoinColumn(name = "id_venta")
	private Ventas venta;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Clientes cliente;
	
	
	//Constructor
	public Facturas() {
		super();
	}


	//Getters y Setters
	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public float getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Ventas getVenta() {
		return venta;
	}

	public void setVenta(Ventas venta) {
		this.venta = venta;
	}
	
	
	//Metodos
	public float calcularMontoFinal() {
		Float monto = 0f;
		for (int i = 0; i < venta.getProductos().size(); i++) {
			float precio = venta.getProductos().get(i).getPrecio() * venta.getCantidad().get(i);
			monto += precio;
		}
		return monto;
	} 
	
}
