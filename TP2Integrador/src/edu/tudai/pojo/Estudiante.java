package edu.tudai.pojo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
	@OneToMany(fetch = FetchType.LAZY)
	private List<Matricula> titulos;
	
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
	

	public List<Matricula> getTitulos() {
		return titulos;
	}

	public void setTitulos(List<Matricula> titulos) {
		this.titulos = titulos;
	}
	
	
//**devuelve todas las @Matricula carreras concluidas*/
	@SuppressWarnings("null")
	public List<Matricula> getConcluidos(){
		List<Matricula> concluido = null;
		for (Matricula m : titulos) {
			if (m.getEgreso() != 0) {
				concluido.add(m);
			}
		}
		return concluido;
	}
	
	//**carga la @param @Matricula.egreso fecha de egreso siendo que se encuentre la @Carrera c*/
	public void  colacionar(int fecha,Carrera c){
		for(Matricula m : titulos) {
			if((m.getCursada().getId_carrera()) == (c.getId_carrera())){
				m.setEgreso(fecha);
			}
		}
	}
	
	@Override
	public String toString() {
		return "Estudiante [id_estudiante=" + id_estudiante + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", edad=" + edad + ", genero=" + genero + ", dni=" + dni + ", residencia=" + residencia + ", nro_lu="
				+ nro_lu + ", titulos=" + titulos + "]";
	}
	
	
}
