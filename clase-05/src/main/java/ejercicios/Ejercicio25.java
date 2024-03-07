package ejercicios;

import java.util.Random;

public class Ejercicio25 {

	public static void main(String[] args) {
		
		/*Escribe un programa que tome dos números como entrada y realice una división. Maneja la excepción en caso de que 
		 * el segundo número sea cero e imprime un mensaje adecuado en caso de que ocurra esta situación.*/

		Random random = new Random();
		
		int numero1 = random.nextInt(10);
		int numero2 = random.nextInt(2);
		
		try {
			float resultado = (float) numero1/numero2;
			System.out.println("El resultado de dividir " + numero1 + " entre " + numero2 + " es: " + resultado);
		} catch (ArithmeticException ex) {
			System.out.println("No se puede dividir por 0");
		}
	}

}
