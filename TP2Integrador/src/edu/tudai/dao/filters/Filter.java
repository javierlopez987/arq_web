package edu.tudai.dao.filters;

import edu.tudai.pojo.Estudiante;

public interface Filter{
	
	boolean cumple(Estudiante e);
	
}
