package tudai.dao.demo;

import java.util.Collection;

import tudai.dao.DAOFactory;
import tudai.dao.DAOPersona;
import tudai.dao.model.Direccion;
import tudai.dao.model.Persona;


public class Programa {
	public static DAOPersona personaDAO;
	
	public static void main(String[] args) {

		
		DAOFactory daoFactory = DAOFactory.getDAOFactory(2);
		
		personaDAO = daoFactory.getDAOPersona();
		
		Direccion d = new Direccion("Toncovich", "Tandil");
		Persona j = new Persona(7, "Javier", 28, 35456123, d);
		Persona i = new Persona(8, "Inés", 26, 37123789, d);
		
		//personaDAO.insertPersona(j);
		//personaDAO.insertPersona(i);
		personaDAO.deletePersona(j);
		
		Collection<Persona> personas = personaDAO.selectPersonas();
		
		for(Persona p: personas) {
			System.out.println(p);
		}
	}
}
