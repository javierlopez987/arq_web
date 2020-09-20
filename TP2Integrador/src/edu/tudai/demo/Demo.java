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
	
	public static void main(String[] args) {

		cargarSistema();
		Sistema programa =  new Sistema();
		System.out.println(programa.getEstudiantesOrderByLastname());
		System.out.println(programa.getEstudiantes());
		Carrera c = Sistema.carreraDAO.findCarrera(3);
		System.out.println(c);
		System.out.println(programa.getEstudiantesByResidencia(c,"Olavarria"));
		System.out.println(programa.getCarrerasConInscriptos());
	}
	
	private static Sistema cargarSistema() {
		/**
		 * Se cargan los datos de estudiantes.csv 
		 * si la tabla en la BD está vacía
		 */
		Collection<Estudiante> estudiantes = Sistema.estudianteDAO.selectEstudiantes();
		if(estudiantes.isEmpty()) {
			cargarEstudiantes();
		}
		
		/**
		 * Se cargan los datos de carreras.csv 
		 * si la tabla en la BD está vacía
		 */
		Collection<Carrera> carreras = Sistema.carreraDAO.selectCarreras();
		if(carreras.isEmpty()) {
			cargarCarreras();			
		}
		
		/**
		 * Se cargan los datos de matriculas.csv 
		 * si la tabla en la BD está vacía
		 */
		Collection<Matricula> matriculas = Sistema.matriculaDAO.selectMatriculas();
		if(matriculas.isEmpty()) {
			cargarMatriculas();			
		}
		
		Sistema programa = new Sistema(carreras, estudiantes);
		
		return programa;
	}

	/**
	 * Función que inserta línea por línea los datos de estudiantes.csv en la BD
	 */
	private static void cargarEstudiantes() {
		String path = "csv/estudiantes.csv";
		
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));

			for (CSVRecord row : parser) {
				int id = new Integer(row.get(0));
				String nombre = row.get(1);
				String apellido = row.get(2);
				int edad = new Integer(row.get(3));
				String genero = row.get(4);
				int nro_lu = new Integer(row.get(5));
				int dni = new Integer(row.get(6));
				String residencia = row.get(7);
				
				Estudiante estudiante = new Estudiante(
						id, nombre, apellido, edad, genero, dni, residencia, nro_lu);
				Sistema.estudianteDAO.insertEstudiante(estudiante);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Función que inserta línea por línea los datos de carreras.csv en la BD
	 */
	private static void cargarCarreras() {
		String path = "csv/carreras.csv";
		
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));

			for (CSVRecord row : parser) {
				String titulo = row.get(0);
				String tipo = row.get(1);
				String unidad_academica = row.get(2);
				
				Carrera carrera = new Carrera(titulo, tipo, unidad_academica);
				Sistema.carreraDAO.insertCarrera(carrera);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Función que inserta línea por línea los datos de matriculas.csv en la BD
	 */
	private static void cargarMatriculas() {
		String path = "csv/matriculas.csv";
		
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));

			for (CSVRecord row : parser) {
				Integer id_estudiante = new Integer(row.get(0));
				Integer id_carrera = new Integer(row.get(1));
				Integer ano_ingreso = new Integer(row.get(2));
				Integer ano_egreso = new Integer(row.get(3));
				
				Carrera cursada = Sistema.carreraDAO.findCarrera(id_carrera);
				Estudiante alumno = Sistema.estudianteDAO.findEstudiante(id_estudiante);
				
				if(cursada != null && alumno != null) {
					Matricula matricula = new Matricula(ano_ingreso, alumno, cursada, ano_egreso);
					Sistema.matriculaDAO.insertMatricula(matricula);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


