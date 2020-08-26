package dev;

import java.util.Collection;

public interface DAOPersona {
	
	int insertPersona(Persona p);
	
	boolean deletePersona(Persona p);
	
	Persona findPersona(Persona p);
	
	boolean updatePersona(Persona p);
	
	Collection<Persona> selectPersonas(int edad);
}
