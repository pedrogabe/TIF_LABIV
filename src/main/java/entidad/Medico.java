package entidad;



public class Medico {
	
	private static final int DIAS = 7;
	private static final int HORAS = 24;
	private Especialidad especialidad;
	private int[][] jorandas = new int[DIAS][HORAS];	
	
	
	public Medico(int dni, String nombre, String apellido, String sexo, String nacionalidad, String fechaNacimiento,
			String direccion, String localidad, String provincia, String eMail, String telefono, Especialidad especialidad, int estado, int[][] jorandas) {
		
		this.especialidad = especialidad;
		this.jorandas = jorandas;
	}


	public Especialidad getEspecialidad() {
		return especialidad;
	}


	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}


	public int[][] getJorandas() {
		return jorandas;
	}


	public void setJorandas(int[][] jorandas) {
		this.jorandas = jorandas;
	}

	
}
