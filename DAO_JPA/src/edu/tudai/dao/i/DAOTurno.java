package edu.tudai.dao.i;

import java.util.Collection;

import edu.tudai.pojo.Persona;
import edu.tudai.pojo.Turno;

public interface DAOTurno {
	
	boolean insertTurno(Turno t);

	boolean deleteTurno(Turno t);

	Turno findTurno(int id);

	boolean updateTurno(Turno t);

	Collection<Turno> selectTurnos();
	
	Collection<Turno> selectTurnos(Persona p);
}
