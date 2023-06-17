package negocioImpl;

import java.util.ArrayList;

import dao.PacienteDao;
import daoImpl.PacienteDaoImpl;
import negocio.PacienteNegocio;
import entidad.Paciente;



public class PacienteNegocioImpl implements PacienteNegocio{
	PacienteDao pdao = new PacienteDaoImpl();
	
	@Override
	public boolean insert(Paciente paciente) {
		boolean estado=false;
		if(paciente.getNombre().trim().length()>0 && paciente.getApellido().trim().length()>0)
		{
			estado=pdao.insert(paciente);
		}
		return estado;
	}
	
	@Override
	public ArrayList<Paciente> readAll(int estado) {		
		return pdao.readAll(estado);
	}

	@Override
	public boolean exists(Paciente paciente) {		
		boolean estado=false;
		if(paciente.getDni() > 0)
		{
		  Paciente p =	pdao.searchPaciente(paciente.getDni());
		  if (p != null)
			  estado = true;
		}
		return estado;
	}

	@Override
	public boolean update(Paciente paciente) {
		boolean update = false;
		if (paciente != null)
			update = pdao.update(paciente, false);
		return update;
	}

	@Override
	public boolean delete(Paciente paciente) {
		boolean update = false;
		if (paciente != null)
			update = pdao.update(paciente, true);
		return update;
	}

}

	