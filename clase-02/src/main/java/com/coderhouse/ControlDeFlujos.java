package com.coderhouse;

public class ControlDeFlujos {

	public static void main(String[] args) {
		
		float nroA = 4;
		float nroB = -2;
		float operacion = nroA / nroB;
		
		System.out.println("Resultado = " + operacion);
		
		if (nroB > 0) {
			System.out.println("Resultado con nros positivos = " + operacion);
		}
		else if (nroB < 0) {
			System.out.println("Resultado con nros negativos= " + operacion);
		}
		else {
			System.out.println("No se puededividir por cero");
		}	
	}
}


