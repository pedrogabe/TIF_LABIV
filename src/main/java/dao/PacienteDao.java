package dao;

import java.util.List;

import dominio.Paciente;

public interface PacienteDao {
	public boolean insert(Paciente paciente);
	public boolean update(Paciente paciente, boolean esBaja);
	public List<Paciente> readAll();
	public int selectCount(Paciente paciente);
}
