package edu.tudai.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.tudai.dao.i.*;
import edu.tudai.dao.jpa.*;
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
	
	/**public List<Carrera> getCarreras(Filter f){
		
	}**/
	
	public List<Estudiante> getEstudiantes(Filter f) {
		List<Estudiante> result = new ArrayList<Estudiante>();
		
		for(Estudiante e: estudiantes) {
			if(f.cumple(e)) {
				result.add(e);
			}
		}
		
		return result;
	}
	
	/**
	 * Esta implementacion del servicio utiliza solo JPQL para dar respuesta.
	 * @return lista de estudiantes ordenados por apellido, nombre.
	 */
	public List<Estudiante> getEstudiantesOrderByLastname() {
		List<Estudiante> result = (List<Estudiante>) ((JPADAOEstudiante) estudianteDAO).selectEstudiantesOrderByLastnameName();
		return result;
	}
	
	public List<Estudiante> getEstudiantesByGenero(String g) {
		List<Estudiante> result = (List<Estudiante>) ((JPADAOEstudiante) estudianteDAO).selectEstudiantesByGenero(g);
		return result;
	}
	
	public List<Estudiante> getEstudiantesByResidencia(Carrera carrera, String residencia) {
		List<Estudiante> result = (List<Estudiante>) ((JPADAOEstudiante) estudianteDAO).selectEstudiantesByResidencia(carrera, residencia);
		return result;
	}
	
	
	/**
	 * Esta implementacion del servicio utiliza JPQL para obtener el Business Object (Estudiante)
	 * y luego se aplica la Business Logic desde Java utilizando POO.
	 * Esta implementación es más flexible, es decir, menos pegada al código. Se podría escalar 
	 * utilizando la interface Comparator.
	 * @return lista de estudiantes ordenados por apellido.
	 */
	public List<Estudiante> getEstudiantes() {
		List<Estudiante> result = (List<Estudiante>) estudianteDAO.selectEstudiantes();
		Collections.sort(result);
		return result;
	}
	
	public Estudiante getEstudiante(int nro_lu) {
		return ((JPADAOEstudiante) estudianteDAO).getEstudiante(nro_lu);
	}
	
	public List<Carrera> getCarrerasConInscriptos() {
		List<Carrera> result = (List<Carrera>) ((JPADAOCarrera) carreraDAO).selectCarrerasConInscriptos();
		return result;
	}
}
