package tudai.dao;

import java.util.Collection;

import tudai.orm.Persona;
import tudai.orm.Turno;

public interface DAOTurno {
	
	boolean insertTurno(Turno t);

	boolean deleteTurno(Turno t);

	Turno findTurno(int id);

	boolean updateTurno(Turno t);

	Collection<Turno> selectTurnos();
	
	Collection<Turno> selectTurnos(Persona p);
}
