package edu.tudai.pojo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Turno {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	@Column
	private Timestamp fecha;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Persona> jugadores;
	
	public Turno() {
		super();
	}

	public Turno(Timestamp fecha) {
		super();
		this.fecha = fecha;
		this.jugadores = new ArrayList<Persona>();
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public Iterator<Persona> getJugadores() {
		return jugadores.iterator();
	}
	
	public void addJugador(Persona p) {
		this.jugadores.add(p);
	}

	@Override
	public String toString() {
		return "Turno [id=" + id + ", fecha=" + fecha + ", jugadores=" + jugadores + "]";
	}
	
}
