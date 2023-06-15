package entidad;

public class Localidad {
	private int idLocalidad;
	private String localidad;	
	private Provincia provincia;
	
	public Localidad(){}
	
	public int getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	public Localidad(int idLocalidad, String localidad, Provincia provincia) {
		this.idLocalidad = idLocalidad;
		this.localidad = localidad;
		this.provincia = provincia;
	}
	@Override
	public String toString() {
		return "Localidad [idLocalidad=" + idLocalidad + ", localidad=" + localidad + ", provincia=" + provincia + "]";
	}
	
}
