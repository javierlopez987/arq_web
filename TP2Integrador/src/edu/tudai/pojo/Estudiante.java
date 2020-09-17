package edu.tudai.pojo;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Estudiante {
	@Id
	private int id_estudiante;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private int edad;
	@Column
	private String genero;
	@Column (nullable = false)
	private int dni;
	@Column
	private String residencia;
	@Column (nullable = false)
	private int nro_lu;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "graduados", cascade = CascadeType.ALL)
	private Map<Carrera, Date> titulos;
	
	public Estudiante() {
		super();
	}

	public Estudiante(int id_estudiante, String nombre, String apellido, int edad, String genero, int dni,
			String residencia, int nro_lu) {
		super();
		this.id_estudiante = id_estudiante;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.residencia = residencia;
		this.nro_lu = nro_lu;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getResidencia() {
		return residencia;
	}

	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}

	public int getNro_lu() {
		return nro_lu;
	}

	public void setNro_lu(int nro_lu) {
		this.nro_lu = nro_lu;
	}

	public int getId_estudiante() {
		return id_estudiante;
	}
	
	public Date getFechaGraduacion(Carrera c) {
		return titulos.get(c);
	}
}
