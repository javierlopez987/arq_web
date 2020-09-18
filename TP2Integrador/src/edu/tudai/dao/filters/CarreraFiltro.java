package edu.tudai.dao.filters;

import edu.tudai.pojo.Carrera;
import edu.tudai.pojo.Estudiante;

public class CarreraFiltro implements Filter{
	
	private Carrera carrera;

	public CarreraFiltro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CarreraFiltro(Carrera carrera) {
		super();
		this.carrera = carrera;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	@Override
	public boolean cumple(Estudiante e) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
