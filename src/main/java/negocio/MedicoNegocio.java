package negocio;

import java.util.ArrayList;

import entidad.Medico;
import entidad.Usuario;

public interface MedicoNegocio {
	public boolean insert(Medico medico);
	public boolean insert(Medico medico, Usuario usuario);
	public boolean delete(Medico medico);
	public boolean update(Medico medico);
	public ArrayList<Medico> readAll(int estado);
	public boolean exists(Medico medico);
}
