package edu.tudai.dao.i;

import java.util.Collection;

import edu.tudai.pojo.Estudiante;
import edu.tudai.pojo.Carrera;

public interface DAOCarrera {
	
	boolean insertCarrera(Carrera t);

	boolean deleteCarrera(Carrera t);

	Carrera findCarrera(int id);

	boolean updateCarrera(Carrera t);

	Collection<Carrera> selectCarreras();
	
	Collection<Carrera> selectCarreras(Estudiante p);
}
