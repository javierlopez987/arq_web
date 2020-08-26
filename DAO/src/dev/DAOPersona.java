package dev;

import java.util.Collection;

public interface DAOPersona {
	
	int insertPersona(String nombre, int edad);
	
	boolean deletePersona(int id);
	
	Persona findPersona(int id);
	
	boolean updatePersona(Persona alguien);
	
	Collection<Persona> selectPersonas(int edad);
}
