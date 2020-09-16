package edu.tudai.dao.i;

import java.util.Collection;

import edu.tudai.pojo.Persona;

public interface DAOPersona {
	
	boolean insertPersona(Persona p);

	boolean deletePersona(Persona p);

	Persona findPersona(int id);

	boolean updatePersona(Persona p);

	Collection<Persona> selectPersonas();
}
