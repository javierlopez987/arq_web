package tudai.dao;

import java.util.Collection;

import tudai.orm.Persona;

public interface DAOPersona {
	
	boolean insertPersona(Persona p);

	boolean deletePersona(Persona p);

	Persona findPersona(int id);

	boolean updatePersona(Persona p);

	Collection<Persona> selectPersonas();
}
