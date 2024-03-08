package com.coderhouse;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		// Instancias de la clase Persona
		
		Persona persona1 = new Persona("Azul", "Colazo");
		Persona persona2 = new Persona("Ignacio", "Noriega");
		Persona persona3 = new Persona("Maria", "Minolfi");
		Persona persona4 = new Persona("Marcela", "Vidal");
		Persona persona5 = new Persona("Uma", "Fraser");
		
		// Creacion de lista de personas
		
		List<Persona> listaDePersonas = new ArrayList<>();
		
		// Agregar Personas en lista 
		
		listaDePersonas.add(persona1);
		listaDePersonas.add(persona2);
		listaDePersonas.add(persona3);
		listaDePersonas.add(persona4);
		listaDePersonas.add(persona5);
		
		// Ordenar la lista alfabeticamente por nombre
		
		listaDePersonas.sort((p1, p2) -> p1.getNombre().compareTo(p2.getNombre()));

		System.out.println("Lista ordenada alfabeticamente por nombre:");
		
		for (Persona persona : listaDePersonas) {
			System.out.println("- Persona: " + persona.getNombre() + " " + persona.getApellido());
		}
		
		// Ordenar la lista alfabeticamente por apellido
		
		listaDePersonas.sort((p1, p2) -> p1.getApellido().compareTo(p2.getApellido()));
		
		System.out.println("\n Lista ordenada alfabeticamente por apellido:");
		
		for (Persona persona : listaDePersonas) {
			System.out.println("- Persona: " + persona.getNombre() + " " + persona.getApellido());
		}
		
		// Ordenar la lista segun apellido en orden inverso
		
		listaDePersonas.sort((p1, p2) -> p2.getApellido().compareTo(p1.getApellido()));
		
		System.out.println("\n Lista ordenada inersamente por apellido:");
		
		for (Persona persona : listaDePersonas) {
			System.out.println("- Persona: " + persona.getNombre() + " " + persona.getApellido());
		}
		
	}

}
