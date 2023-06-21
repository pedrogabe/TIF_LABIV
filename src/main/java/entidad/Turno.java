package entidad;

public class Turno {
	
	private int idTurno;
	private Medico medico;
	private Paciente paciente;
	private String fechaReserva;
	private String observacion;
	private int idEstadoTurno;
	
	public Turno(int idTurno, Medico medico, Paciente paciente, String fechaReserva, String observacion,
			int idEstadoTurno) {
		this.idTurno = idTurno;
		this.medico = medico;
		this.paciente = paciente;
		this.fechaReserva = fechaReserva;
		this.observacion = observacion;
		this.idEstadoTurno = idEstadoTurno;
	}
	
	public int getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public String getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(String fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public int getIdEstadoTurno() {
		return idEstadoTurno;
	}
	public void setIdEstadoTurno(int idEstadoTurno) {
		this.idEstadoTurno = idEstadoTurno;
	}
	
	@Override
	public String toString() {
		return "Turno [idTurno=" + idTurno + ", medico=" + medico.getApellido() + ", paciente=" + paciente.getApellido() + ", fechaReserva="
				+ fechaReserva + ", observacion=" + observacion + ", idEstadoTurno=" + idEstadoTurno + "]";
	}
	
}
