package edu.tudai.demo;

import edu.tudai.dao.i.*;
import edu.tudai.pojo.*;


public class Demo {
	public static DAOFactory daoFactory = DAOFactory.getDAOFactory(2);
	public static DAOEstudiante estudianteDAO = daoFactory.getDAOEstudiante();
	public static DAOCarrera carreraDAO = daoFactory.getDAOCarrera();
	
	public static void main(String[] args) {

		Estudiante i = estudianteDAO.findEstudiante(2);
		System.out.println(i);
		
	}
}
