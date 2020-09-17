package edu.tudai.demo;

import java.util.Collection;

import edu.tudai.pojo.*;

public class Sistema {
	private Collection<Carrera> carreras;
	private Collection<Estudiante> estudiantes;
	
	public Sistema() {
		super();
	}

	public Sistema(Collection<Carrera> carreras, Collection<Estudiante> estudiantes) {
		super();
		this.carreras = carreras;
		this.estudiantes = estudiantes;
	}
	
	public void addEstudiante(Estudiante e) {
		try {
			estudiantes.add(e);			
		} catch (Exception exc) {
			System.out.println(exc);
		}
	}
	
	public void addCarrera(Carrera c) {
		try {
			carreras.add(c);
		} catch (Exception exc) {
			System.out.println(exc);
		}
	}
	
	
	
}
