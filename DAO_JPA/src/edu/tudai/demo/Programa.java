package edu.tudai.demo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;

import edu.tudai.dao.i.DAOFactory;
import edu.tudai.dao.i.DAOPersona;
import edu.tudai.dao.i.DAOTurno;
import edu.tudai.pojo.Direccion;
import edu.tudai.pojo.Persona;
import edu.tudai.pojo.Turno;


public class Programa {
	public static DAOFactory daoFactory = DAOFactory.getDAOFactory(2);
	public static DAOPersona personaDAO = daoFactory.getDAOPersona();
	public static DAOTurno turnoDAO = daoFactory.getDAOTurno();
	
	public static void main(String[] args) {

		Persona i = personaDAO.findPersona(2);
		System.out.println(i);
		
		Persona x = new Persona(2, "Carolina", 35, 31489623, new Direccion("Segundo Sombras", "Tandil"));
		personaDAO.updatePersona(x);
		
		System.out.println(x);
	}
}
