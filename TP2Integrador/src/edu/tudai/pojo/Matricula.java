package edu.tudai.pojo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import edu.tudai.util.MatriculaId;

@Entity
public class Matricula {
	@EmbeddedId
	private MatriculaId id_matricula = new MatriculaId();
	@ManyToOne
	@MapsId("id_estudiante")
	private Estudiante alumno;
	@ManyToOne
	@MapsId("id_carrera")
	private Carrera cursada;
	@Column
	private int ingreso;
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
	public Matricula(int ano_ingreso, Estudiante alumno, Carrera cursada, int ano_egreso) {
		super();
		this.ingreso = ano_ingreso;
		this.alumno = alumno;
		this.cursada = cursada;
		this.egreso = ano_egreso;
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
		return "Matricula [id_matricula=" + id_matricula + ", ingreso=" + ingreso + ", alumno=" + alumno + ", cursada="
				+ cursada + ", egreso=" + egreso + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alumno == null) ? 0 : alumno.hashCode());
		result = prime * result + ((cursada == null) ? 0 : cursada.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matricula other = (Matricula) obj;
		if (alumno == null) {
			if (other.alumno != null)
				return false;
		} else if (!alumno.equals(other.alumno))
			return false;
		if (cursada == null) {
			if (other.cursada != null)
				return false;
		} else if (!cursada.equals(other.cursada))
			return false;
		return true;
	}
	
	
	
}
