package dao;

import java.util.ArrayList;

import entidad.Medico;

public interface MedicoDao {
	public boolean insert(Medico medico);
	public boolean update(Medico medico, boolean esBaja);
	public ArrayList<Medico> readAll(int estado);
	public Medico searchMedico(int dni);
	public int selectCount(Medico medico);
	public Medico searchMedicoUsuario(int IdUsuario);
}
