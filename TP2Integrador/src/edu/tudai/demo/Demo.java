package edu.tudai.demo;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.tudai.pojo.*;


public class Demo {
	private static Sistema programa =  new Sistema();
	
	public static void main(String[] args) {
		
		// El conjunto de datos iniciales para la base de datos se cargará desde un archivo CSV.
		cargarBD();
		
		// a) dar de alta un estudiante
		altaEstudiante();
		
		// b) matricular un estudiante en una carrera
		matricularEstudiante();
		
		// c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
		imprimirEstudiantesPorApellido();
		
		// d) recuperar un estudiante, en base a su número de libreta universitaria.
		imprimirEstudianteLibreta(45455);
		
		// e) recuperar todos los estudiantes, en base a su género.
		imprimirEstudiantesGeneroMasculino();
		
		// f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
		imprimirInformeInscriptosCarreras();
		
		// g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
		Carrera c = Sistema.carreraDAO.findCarrera(3);
		imprimirEstudiantesByResidencia(c, "Olavarria");
		
		// 3) Generar un reporte de las carreras, que para cada carrera incluya información de los
		// inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
		// los años de manera cronológica.
		imprimirInformeCarrerasInscriptosPorAnio();
	}
	
	/**
	 * Testea el servicio alta estudiante y lo imprime por pantalla si es exitoso.
	 */
	private static void altaEstudiante() {
		Estudiante e = new Estudiante(2001, "Pedro", "Escamoso", 42, "Masculino", 26159267, "Tandil", 99999);
		programa.addEstudiante(e);
		System.out.println(programa.getEstudiante(99999));
	}
	
	private static void matricularEstudiante() {
		Estudiante e = programa.getEstudiante(45455);
		Carrera c = programa.getCarrera(3);
		Matricula m = programa.matricular(e, c, 2020);
		System.out.println(m);
	}
	
	private static void imprimirEstudiantesPorApellido() {
		List<Estudiante> estudiantes = programa.getEstudiantesOrderByLastname();
		for(Estudiante e: estudiantes) {
			System.out.println(e);
		}
	}
	
	private static void imprimirEstudianteLibreta(int nro_lu) {
		Estudiante e = programa.getEstudiante(nro_lu);
		System.out.println(e);
	}
	
	private static void imprimirEstudiantesGeneroMasculino() {
		List<Estudiante> estudiantes = programa.getEstudiantesByGenero("Masculino");

		for(Estudiante e: estudiantes) {
			System.out.println(e);
		}
		
	}
	
	/**
	 * Imprime carreras con estudiantes inscriptos, 
	 * ordenadas por cantidad de inscriptos
	 */
	private static void imprimirInformeCarrerasInscriptosPorAnio() {
		
		programa.imprimirCarrerasInscriptosPorAnio();
	}
	
	/**
	 * Imprime carreras con estudiantes inscriptos, 
	 * ordenadas por cantidad de inscriptos
	 */
	private static void imprimirInformeInscriptosCarreras() {
		
		List<Carrera> carreras = programa.getCarrerasConInscriptos();
		for(Carrera c: carreras) {
			System.out.println("------");
			System.out.println(c);
			System.out.println("Inscriptos: " + c.getCantInscriptos());
			System.out.println("------");
		}
	}
	
	private static void imprimirEstudiantesByResidencia(Carrera c, String residencia) {
		
		List<Estudiante> estudiantes = programa.getEstudiantesByResidencia(c,residencia);
		for(Estudiante e: estudiantes) {
			System.out.println("------");
			System.out.println(e);
			System.out.println("De: "+residencia);
			System.out.println("------");
		}
	}
	
	
	private static void imprimirEstudiantesByGenero(String genero) {
		
		List<Estudiante> estudiantes = programa.getEstudiantesByGenero(genero);
		for(Estudiante e: estudiantes) {
			System.out.println("------");
			System.out.println(e);
			System.out.println("De: "+genero);
			System.out.println("------");
		}
	}
	
	/**
	 * Carga la información desde los csv hacia la BD
	 */
	private static void cargarBD() {
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
		
	}

	/**
	 * Función que inserta línea por línea los datos de estudiantes en la BD
	 */
	private static void cargarEstudiantes() {
		String path = "csv/estudiantes_export.csv";
		
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
	 * Función que inserta línea por línea los datos de carreras en la BD
	 */
	private static void cargarCarreras() {
		String path = "csv/carreras_export.csv";
		
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
	 * Función que inserta línea por línea los datos de matriculas en la BD
	 */
	private static void cargarMatriculas() {
		String path = "csv/matriculas_export.csv";
		
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


