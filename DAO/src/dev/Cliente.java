package dev;

import java.util.Collection;

public class Cliente {

	public static void main(String[] args) {
		DAOFactory mysqlFactory = DAOFactory.getDAOFactory();
		
		DAOPersona persDAO = mysqlFactory.getDAOPersona();
		
		int edadBuscada = 35;
		
		Collection<Persona> personas = persDAO.selectPersonas(edadBuscada);
		
		for(Persona p: personas) {
			System.out.println(p);
		}

	}

}
