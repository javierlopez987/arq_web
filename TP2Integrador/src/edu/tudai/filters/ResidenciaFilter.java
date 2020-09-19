package edu.tudai.filters;

import edu.tudai.pojo.Estudiante;

public class ResidenciaFilter implements Filter{
	
	private String residencia;
	
	

	public ResidenciaFilter() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ResidenciaFilter(String residencia) {
		super();
		this.residencia = residencia;
	}



	public String getResidencia() {
		return residencia;
	}



	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}



	@Override
	public boolean cumple(Estudiante e) {
		if (e.getResidencia() == this.residencia) {
			return true;
		}
		return false;
	}

}
