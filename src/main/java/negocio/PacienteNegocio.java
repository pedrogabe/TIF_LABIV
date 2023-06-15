package negocio;

import java.util.List;

import entidad.Paciente;


public interface PacienteNegocio {
	public boolean insert(Paciente paciente);
	public boolean delete(Paciente paciente);
	public List<Paciente> readAll();
	public boolean exists(Paciente paciente);
}






