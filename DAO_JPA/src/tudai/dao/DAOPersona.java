package tudai.dao;

import java.util.Collection;

import tudai.dao.model.Persona;

public interface DAOPersona {
	int insertPersona(Persona p);

	boolean deletePersona(Persona p);

	Persona findPersona(int id);

	boolean updatePersona(Persona p);

	Collection<Persona> selectPersonas();
}
