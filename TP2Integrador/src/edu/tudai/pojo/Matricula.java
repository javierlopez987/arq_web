package edu.tudai.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Matricula {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int idMatricula;
	@Column
	private int ingreso;
	@ManyToOne(fetch = FetchType.LAZY)
	private Estudiante alumno;
	@ManyToOne
	private Carrera cursada;
	@Column
	private int egreso;
	
	public Matricula() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Matricula(int ano_ingreso, Estudiante alumno, Carrera cursada) {
		super();
		this.ingreso = ano_ingreso;
		this.alumno = alumno;
		this.cursada = cursada;
		this.egreso = 0;
	}
	public int getIngreso() {
		return ingreso;
	}
	public void setIngreso(int ano_ingreso) {
		this.ingreso = ano_ingreso;
	}
	public Estudiante getInscripto() {
		return alumno;
	}
	public void setInscripto(Estudiante alumno) {
		this.alumno = alumno;
	}
	public Carrera getCursada() {
		return cursada;
	}
	public void setCursada(Carrera cursada) {
		this.cursada = cursada;
	}
	
	public int getEgreso() {
		return egreso;
	}
	public void setEgreso(int egreso) {
		this.egreso = egreso;
	}
	@Override
	public String toString() {
		return "Matricula [idMatricula=" + idMatricula + ", ingreso=" + ingreso + ", alumno=" + alumno + ", cursada="
				+ cursada + ", egreso=" + egreso + "]";
	}
	
	
	
}