package entidad;

public class Especialidad {

	private int idEspecialidad;
	private String especialidad;
	
		
	public Especialidad(int idEspecialidad, String especialidad) {
		super();
		this.idEspecialidad = idEspecialidad;
		this.especialidad = especialidad;
	}
	
	public int getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public String toString() {
		return "Especialidad [idEspecialidad=" + idEspecialidad + ", especialidad=" + especialidad + "]";
	}
	
	
}
