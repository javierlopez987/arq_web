package edu.tudai.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.tudai.dao.i.DAOCarrera;
import edu.tudai.dao.i.DAOEstudiante;
import edu.tudai.dao.i.DAOFactory;
import edu.tudai.dao.i.DAOMatricula;
import edu.tudai.filters.Filter;
import edu.tudai.pojo.*;

public class Sistema {
	public static DAOFactory daoFactory = DAOFactory.getDAOFactory(2);
	public static DAOEstudiante estudianteDAO = daoFactory.getDAOEstudiante();
	public static DAOCarrera carreraDAO = daoFactory.getDAOCarrera();
	public static DAOMatricula matriculaDAO = daoFactory.getDAOMatricula();
	
	private Collection<Carrera> carreras;
	private Collection<Estudiante> estudiantes;
	
	public Sistema() {
		super();
	}

	public Sistema(Collection<Carrera> carreras, Collection<Estudiante> estudiantes) {
		this.carreras = carreras;
		this.estudiantes = estudiantes;
	}
	
	/**
	 * Si es posible persistir al estudiante, lo da de alta en el sistema
	 * 
	 * @param e Objeto Estudiante
	 * @return true si fue posible persistir al estudiante
	 */
	public boolean addEstudiante(Estudiante e) {
		boolean success = false;
		
		try {
			if(estudianteDAO.insertEstudiante(e)) {
				estudiantes.add(e);				
				success = true;
			}
		} catch (Exception exc) {
			success = false;
			System.out.println(exc);
		}
		
		return success;
	}
	
	public boolean addCarrera(Carrera c) {
		boolean success = false;
		
		try {
			if(carreraDAO.insertCarrera(c)) {
				carreras.add(c);				
				success = true;
			}
		} catch (Exception exc) {
			success = false;
			System.out.println(exc);
		}
		
		return success;
	}
	
	public Matricula matricular(Estudiante e, Carrera c, int ano_ingreso) {
		return c.matricular(e, ano_ingreso);
	}
	
	public Map<Carrera, Integer> getCarreras() {
		Map<Carrera, Integer> result = new HashMap<Carrera, Integer>();
		for(Carrera c: carreras) {
			if(c.tieneInscriptos()) {
				Integer cant = c.getCantInscriptos();
				result.put(c, cant);
			}
		}
		return result;
	}
	
	public List<Estudiante> getEstudiantes(Filter f) {
		List<Estudiante> result = new ArrayList<Estudiante>();
		
		for(Estudiante e: estudiantes) {
			if(f.cumple(e)) {
				result.add(e);
			}
		}
		
		return result;
	}
	
}
