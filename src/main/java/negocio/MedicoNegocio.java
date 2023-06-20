package negocio;

import java.util.ArrayList;

import entidad.Medico;

public interface MedicoNegocio {
	public boolean insert(Medico medico);
	public boolean delete(Medico medico);
	public boolean update(Medico medico);
	public ArrayList<Medico> readAll(int estado);
	public boolean exists(Medico medico);
}
