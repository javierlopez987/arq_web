package edu.tudai.demo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.tudai.dao.i.*;
import edu.tudai.pojo.*;


public class Demo {
	public static DAOFactory daoFactory = DAOFactory.getDAOFactory(2);
	public static DAOEstudiante estudianteDAO = daoFactory.getDAOEstudiante();
	public static DAOCarrera carreraDAO = daoFactory.getDAOCarrera();	
	
	public static void main(String[] args) {

		/**
		 * Se cargan los datos de estudiantes.csv 
		 * si la tabla en la BD está vacía
		 */
		Collection<Estudiante> estudiantes = estudianteDAO.selectEstudiantes();
		if(estudiantes.isEmpty()) {
			cargarEstudiantes();
		}
		
		/**
		 * Se cargan los datos de carreras.csv 
		 * si la tabla en la BD está vacía
		 */
		Collection<Carrera> carreras = carreraDAO.selectCarreras();
		if(carreras.isEmpty()) {
			cargarCarreras();			
		}

		Sistema programa = new Sistema(carreras, estudiantes);
	}
	
	/**
	 * Función que inserta línea por línea los datos de estudiantes.csv en la BD
	 */
	public static void cargarEstudiantes() {
		String path = "csv/estudiantes.csv";
		
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));

			for (CSVRecord row : parser) {
				int id = new Integer(row.get(1));
				String nombre = row.get(2);
				String apellido = row.get(3);
				int edad = new Integer(row.get(4));
				String genero = row.get(5);
				int dni = new Integer(row.get(6));
				String residencia = row.get(7);
				int nro_lu = new Integer(row.get(8));
				
				Estudiante estudiante = new Estudiante(
						id, nombre, apellido, edad, genero, dni, residencia, nro_lu);
				estudianteDAO.insertEstudiante(estudiante);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Función que inserta línea por línea los datos de carreras.csv en la BD
	 */
	public static void cargarCarreras() {
		String path = "csv/carreras.csv";
		
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));

			for (CSVRecord row : parser) {
				String titulo = row.get(1);
				String tipo = row.get(2);
				String unidad_academica = row.get(3);
				
				Carrera carrera = new Carrera(titulo, tipo, unidad_academica);
				carreraDAO.insertCarrera(carrera);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


