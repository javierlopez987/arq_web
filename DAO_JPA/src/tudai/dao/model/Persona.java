package tudai.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Persona {
	@Id
	private int id;
	@Column (nullable = false)
	private String nombre;
	@Column (name = "anios")
	private int edad;
	@Column
	private int dni;
	
	public Persona() {
		super();
	}

	public Persona(int id, String nombre, int edad, int dni) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.dni = dni;
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

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", dni=" + dni + "]";
	}
	
	
}
