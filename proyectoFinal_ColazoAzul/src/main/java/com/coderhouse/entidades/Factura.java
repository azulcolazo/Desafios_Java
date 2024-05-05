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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "factura")
public class Factura {
	
	//Campos - Atributos
	@Id
	@Column(name = "id_factura")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFactura;
	@Column(name = "metodo_pago")
	private String metodoPago;
	@Column(name = "fecha")
	private LocalDate fecha;
	@Column(name = "monto_final")
	private float montoFinal;
	
	private boolean procesada;
	
	
	//Relaciones entre tablas
	@OneToMany(mappedBy = "factura")
	private List<Linea> lineas;
	
	@OneToOne(mappedBy = "factura")
	private Venta venta;
	
	@ManyToOne
	@JoinColumn(name = "cliente")
	private Cliente cliente;
	
	
	//Constructor
	public Factura() {
		super();
	}


	public Factura(int idFactura, String metodoPago, LocalDate fecha, float montoFinal, boolean procesada,
			List<Linea> lineas, Venta venta, Cliente cliente) {
		super();
		this.idFactura = idFactura;
		this.metodoPago = metodoPago;
		this.fecha = fecha;
		this.montoFinal = montoFinal;
		this.procesada = procesada;
		this.lineas = lineas;
		this.venta = venta;
		this.cliente = cliente;
	}



	//Getters y Setters
	public LocalDate getFecha() {
		return fecha;
	}

	
	public void setFecha(LocalDate fecha) {
		if(!procesada) {
			this.fecha = fecha;
		}
	}

	
	public float getMontoFinal() {
		return montoFinal;
	}
	
	
	public void setMontoFinal(float montoFinal) {
		if(!procesada) {
			this.montoFinal = montoFinal;
		}
	}
	
	
	public boolean isProcesada() {
		return procesada;
	}
	
	
	public void setProcesada(boolean procesada) {
		if(!this.procesada) {
			this.procesada = procesada;
		}
	}
	
	
	public List<Linea> getLineas() {
		return lineas;
	}
	
	
	public void setLineas(List<Linea> lineas) {
		if(!procesada) {
			this.lineas = lineas;
		}
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}
	
	
	public void setCliente(Cliente cliente) {
		if(!procesada) {
			this.cliente = cliente;
		}
	}
	
	
	public int getIdFactura() {
		return idFactura;
	}
	
	
	public String getMetodoPago() {
		return metodoPago;
	}
	
	
	public void setIdFactura(int idFactura) {
		if(!procesada) {
			this.idFactura = idFactura;
		}
	}
	
	
	public void setMetodoPago(String metodoPago) {
		if(!procesada) {
			this.metodoPago = metodoPago;
		}
	}
	
	
	//Metodos
	public float calcularMontoFinal() {
		float monto = 0;
		for(Linea linea : lineas) {
			monto += linea.getProducto().getPrecio() * linea.getCantidad();
		}
		return monto;
	}

}
