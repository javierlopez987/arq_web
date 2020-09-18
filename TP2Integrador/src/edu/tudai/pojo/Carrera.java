package edu.tudai.pojo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Carrera {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id_carrera;
	@Column
	private String titulo;
	@Column
	private String tipo;
	@Column
	private String unidad_academica;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Matricula> matriculas;
	
	public Carrera() {
		super();
	}

	public Carrera(String titulo, String tipo, String unidad_academica) {
		super();
		this.titulo = titulo;
		this.tipo = tipo;
		this.unidad_academica = unidad_academica;
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

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}


	public int getId_carrera() {
		return id_carrera;
	}
	//** Devuelve una lista de @Matricula en estado graduados*/
	@SuppressWarnings("null")
	public List<Matricula> getGraduados(){
		List<Matricula> graduados = null;
		for (Matricula m : matriculas) {
			if (m.getEgreso() != 0) {
				graduados.add(m);
			}
		}
		return graduados;
	}
	
	//**devuelve los @Matricula graduados en una fecha dada*/
	public List<Matricula> getGraduados(int fecha){
		List<Matricula> graduados = null;
		for (Matricula m : matriculas) {
			if (m.getEgreso() == fecha) {
				graduados.add(m);
			}
		}
		return graduados;
	}

	@Override
	public String toString() {
		return "Carrera [id_carrera=" + id_carrera + ", titulo=" + titulo + ", tipo=" + tipo + ", unidad_academica="
				+ unidad_academica + ", matriculas=" + matriculas + "]";
	}

}
