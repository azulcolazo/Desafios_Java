package com.coderhouse;
public class Variables {

	public static void main(String[] args) {
		byte x;
		int y;
		x = 23;
		y = x;
		
		System.out.println("El valor de la variable es: " + y + "\n");
		
		float a = 2;
		int b = (int) a;
		
		System.out.println("El valor de la variable float es: " + a);
		System.out.println("El valor de la variable float (casteada) es: " + b + "\n");
		
		char letra = 'a';
		String letras = "Hola mundo";
		
		System.out.println("El valor de la variable char es: " + letra);
		System.out.println("El valor de la variable  String es: " + letras + "\n");
		
		boolean falso = false;
		boolean verdadero = true;
	}

}
