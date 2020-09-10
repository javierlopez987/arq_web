package tudai.dao.demo;

import java.util.Collection;

import tudai.dao.DAOFactory;
import tudai.dao.DAOPersona;
import tudai.dao.model.Persona;


public class Programa {

	public static void main(String[] args) {

		DAOFactory mysqlFactory = DAOFactory.getDAOFactory(2);
		
		DAOPersona persDAO = mysqlFactory.getDAOPersona();
		
		Persona j = new Persona(5, "Javier", 28, 35456123);
		Persona i = new Persona(6, "Inés", 26, 37123789);
		
		//persDAO.insertPersona(j);
		//persDAO.insertPersona(i);
		
		Collection<Persona> personas = persDAO.selectPersonas();
		
		for(Persona p: personas) {
			System.out.println(p);
		}
	}
}
