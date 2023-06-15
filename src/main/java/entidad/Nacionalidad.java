package entidad;

public class Nacionalidad {
	private int idNacionalidad;
	private String nacionalidad;
	
	public Nacionalidad(){}
	
	public int getIdNacionalidad() {
		return idNacionalidad;
	}
	public void setIdNacionalidad(int idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	public Nacionalidad(int idNacionalidad, String nacionalidad) {
		this.idNacionalidad = idNacionalidad;
		this.nacionalidad = nacionalidad;
	}
	@Override
	public String toString() {
		return "Nacionalidad [idNacionalidad=" + idNacionalidad + ", nacionalidad=" + nacionalidad + "]";
	}
	
	
}
