package edu.tudai.dao.filters;

import edu.tudai.pojo.Estudiante;

public class GeneroFilter implements Filter{
	
	private String genero;

	public GeneroFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GeneroFilter(String genero) {
		super();
		this.genero = genero;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}


	@Override
	public boolean cumple(Estudiante e) {
		if (e.getGenero() == this.genero) {
			return true;
		}
		return false;
	}
}


