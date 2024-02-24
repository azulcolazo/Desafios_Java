package com.coderhouse;

public class OperadorTernario {

	public static void main(String[] args) {
		
		String mensaje1 = "Hola Mundo";
		String mensaje2 = "Adios Mundo";
		boolean condicion = true;
		String mensajeFinal;
		
		mensajeFinal = condicion 
				? //if
				mensaje1 
				: //else
				mensaje2;
		
		System.out.println("mensaje = " + mensajeFinal);
	}

}
