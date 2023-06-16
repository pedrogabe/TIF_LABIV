package negocioImpl;

import java.util.List;

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
	
	public boolean delete(Paciente paciente_a_eliminar) {
		
		boolean estado=false;
		if(paciente_a_eliminar.getDni() >0)
		{
			estado=pdao.update(paciente_a_eliminar, true);
		}
		return estado;
	}
	
	@Override
	public List<Paciente> readAll() {		
		return pdao.readAll();
	}

	@Override
	public boolean exists(Paciente paciente) {		
		boolean estado=false;
		if(paciente.getDni() > 0)
		{
		  Paciente p =	pdao.searachPaciente(paciente.getDni());
		  if (p != null)
			  estado = true;
		}
		return estado;
	}

}

	