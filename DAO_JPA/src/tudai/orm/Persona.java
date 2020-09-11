package tudai.orm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
	@ManyToOne(cascade = CascadeType.ALL)
	private Direccion domicilio;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "jugadores")
	private List<Turno> turnos;
	
	public Persona() {
		super();
	}

	public Persona(int id, String nombre, int edad, int dni, Direccion domicilio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.dni = dni;
		this.domicilio = domicilio;
		this.turnos = new ArrayList<Turno>();
	}

	public Direccion getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Direccion domicilio) {
		this.domicilio = domicilio;
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
