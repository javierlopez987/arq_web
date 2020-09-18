package edu.tudai.dao.filters;

import edu.tudai.pojo.Estudiante;

public class LuFilter implements Filter{
	
	private int lu;
	
	

	public LuFilter() {
		super();
		// TODO Auto-generated constructor stub
	}



	public LuFilter(int lu) {
		super();
		this.lu = lu;
	}



	public int getLu() {
		return lu;
	}



	public void setLu(int lu) {
		this.lu = lu;
	}



	@Override
	public boolean cumple(Estudiante e) {
		if (e.getNro_lu() == this.lu) {
			return true;
		}
		return false;
	}
	
	

}
