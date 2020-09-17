package edu.tudai.pojo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
	@ManyToMany(fetch = FetchType.LAZY)
	private Map<Integer, List<Estudiante>> cohortes;
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Estudiante> graduados;
	
	public Carrera() {
		super();
	}

	public Carrera(String titulo, String tipo, String unidad_academica) {
		super();
		this.titulo = titulo;
		this.tipo = tipo;
		this.unidad_academica = unidad_academica;
	}
	
	
}
