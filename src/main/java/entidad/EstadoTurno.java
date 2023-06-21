package entidad;

public class EstadoTurno {

	private int idEstadoTurno;
	private String descripcion;
	
	public EstadoTurno(int idEstadoTurno, String descripcion) {		
		this.idEstadoTurno = idEstadoTurno;
		this.descripcion = descripcion;
	}
	
	public int getIdEstadoTurno() {
		return idEstadoTurno;
	}
	public void setIdEstadoTurno(int idEstadoTurno) {
		this.idEstadoTurno = idEstadoTurno;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "EstadoTurno [idEstadoTurno=" + idEstadoTurno + ", descripcion=" + descripcion + "]";
	}	
	
}
