package dao;

import java.util.ArrayList;

import entidad.Paciente;

public interface PacienteDao {
	public boolean insert(Paciente paciente);
	public boolean update(Paciente paciente, boolean esBaja);
	public ArrayList<Paciente> readAll(int estado);
	public Paciente searchPaciente(int dni);
	public int selectCount(Paciente paciente);
}
