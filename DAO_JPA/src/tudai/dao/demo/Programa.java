package tudai.dao.demo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;

import tudai.dao.DAOFactory;
import tudai.dao.DAOPersona;
import tudai.dao.DAOTurno;
import tudai.orm.Direccion;
import tudai.orm.Persona;
import tudai.orm.Turno;


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
