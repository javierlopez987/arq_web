package edu.tudai.filters;

import edu.tudai.pojo.Carrera;
import edu.tudai.pojo.Estudiante;
import edu.tudai.pojo.Matricula;

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
		
		for (Matricula m: e.getTitulos()) {
			if (m.getCursada().getId_carrera() == this.carrera.getId_carrera()) {
				return true;
			}
		}
		return false;
	}

}
