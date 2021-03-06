package edu.tudai.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import edu.tudai.demo.Sistema;

@Entity
public class Carrera {
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String titulo;
	@Column
	private String tipo;
	@Column
	private String unidad_academica;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cursada")
	private List<Matricula> matriculas;
	@Transient
	private int inscriptos;
	@Transient
	private Map<Integer, Integer> inscriptosPorAnio;
	
	public Carrera() {
		super();
	}

	public Carrera(String titulo, String tipo, String unidad_academica) {
		super();
		this.titulo = titulo;
		this.tipo = tipo;
		this.unidad_academica = unidad_academica;
	}

	public int getId_carrera() {
		return id;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUnidad_academica() {
		return unidad_academica;
	}

	public void setUnidad_academica(String unidad_academica) {
		this.unidad_academica = unidad_academica;
	}
	
	public int getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(int inscriptos) {
		this.inscriptos = inscriptos;
	}
	
	public Map<Integer, Integer> getInscriptosPorAnio() {
		return inscriptosPorAnio;
	}

	public void addInscriptosPorAnio(Integer anio, Integer inscriptos) {
		if(this.inscriptosPorAnio == null) {
			this.inscriptosPorAnio = new HashMap<Integer, Integer>();
		}
		this.inscriptosPorAnio.put(anio, inscriptos);
	}

	public List<Matricula> getMatriculas() {
		List<Matricula> copy = new ArrayList<Matricula>(matriculas);
		return copy;
	}
	
	public Matricula matricular(Estudiante e, int ingreso) {
		Matricula nueva = new Matricula(ingreso, e, this);
		if(!matriculas.contains(nueva)) {
			Sistema.matriculaDAO.insertMatricula(nueva);
			matriculas.add(nueva);	
		} else {
			nueva = null;
		}
		return nueva;
	}
	
	/**
	 * Devuelve true si al menos una vez un alumno fue matriculado
	 * dado que matriculas conserva el registro histórico de la carrera
	 */
	public boolean tieneInscriptos() {
		return !matriculas.isEmpty();
	}
	
	public int getCantInscriptos() {
		return matriculas.size();
	}
	
	//** Devuelve una lista de @Matricula en estado graduados*/
	public List<Matricula> getGraduados(){
		List<Matricula> graduados = new ArrayList<Matricula>();
		for (Matricula m : matriculas) {
			if (m.getEgreso() != 0) {
				graduados.add(m);
			}
		}
		return graduados;
	}
	
	//**devuelve los @Matricula graduados en una fecha dada*/
	public List<Matricula> getGraduados(int fecha){
		List<Matricula> graduados = new ArrayList<Matricula>();
		for (Matricula m : matriculas) {
			if (m.getEgreso() == fecha) {
				graduados.add(m);
			}
		}
		return graduados;
	}

	@Override
	public String toString() {
		return "Carrera [id_carrera=" + id + ", titulo=" + titulo + ", tipo=" + tipo + ", unidad_academica="
				+ unidad_academica + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrera other = (Carrera) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
