package edu.tudai.dao.filters;

import edu.tudai.pojo.Estudiante;

public class AndFilter implements Filter{
	
	private Filter f1;
	private Filter f2;
	
	
	
	
	public AndFilter() {
		super();
		// TODO Auto-generated constructor stub
	}




	public AndFilter(Filter f1, Filter f2) {
		super();
		this.f1 = f1;
		this.f2 = f2;
	}




	public Filter getF1() {
		return f1;
	}




	public void setF1(Filter f1) {
		this.f1 = f1;
	}




	public Filter getF2() {
		return f2;
	}




	public void setF2(Filter f2) {
		this.f2 = f2;
	}




	@Override
	public boolean cumple(Estudiante e) {
		if (f1.cumple(e) && f2.cumple(e)) {
			return true;
		}
		return false;
	}
	
	

}
