package edu.tudai.dao.i;

import java.util.Collection;

import edu.tudai.pojo.Carrera;
import edu.tudai.pojo.Estudiante;
import edu.tudai.pojo.Matricula;

public interface DAOMatricula {
	
	boolean insertMatricula(Matricula m);
	
	boolean deleteMatricula(Matricula m);
	
	Matricula findMatricula(int id);
	
	boolean updateMatricula(Matricula m);
	
	Collection<Matricula> selectMatriculas();
	
	Collection<Matricula> selectMatriculas(Estudiante e);
	
	Collection<Matricula> selectMatriculas(Carrera c);
}
