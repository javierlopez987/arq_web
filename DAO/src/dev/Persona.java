package dev;

public class Persona {
	private int id;
	private String nombre;
	private int edad;
	private int dni;
	
	public Persona(String nombre, int edad, int dni) {
		this.nombre = nombre;
		this.edad = edad;
		this.dni = dni;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getDni() {
		return dni;
	}
	
	public String toString() {
		return "Nombre: " + this.nombre + " Edad: " + this.edad + " DNI: " + this.dni;
	}
}
