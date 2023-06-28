package negocioImpl;

import java.time.DayOfWeek;
import java.util.ArrayList;

import dao.*;
import daoImpl.*;
import entidad.Jornada;
import entidad.Medico;
import entidad.Paciente;
import entidad.Usuario;
import negocio.*;

public class MedicoNegocioImpl implements MedicoNegocio {

	MedicoDao medicoDaoImpl = null;

	@Override
	public boolean insert(Medico medico) {
		medicoDaoImpl = new MedicoDaoImpl();
		boolean insertado = false;
		if (medico.getIdUsuario() > 0 && medico.getNombre().trim() != "" && medico.getApellido().trim() != ""
				&& medico.getDni() > 0) {
			insertado = medicoDaoImpl.insert(medico);
		}
		return insertado;
	}

	@Override
	public boolean insert(Medico medico, Usuario usuario) {
		boolean insertOk = false;
		UsuarioDao usuarioDaoImpl = new UsuarioDaoImpl();
		try {
			insertOk = usuarioDaoImpl.insert(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (insertOk) {
			usuario.setIdUsuario(usuarioDaoImpl.selectMaxId());
			medico.setIdUsuario(usuario.getIdUsuario());
			insertOk = insert(medico);
			if (!insertOk) {
				usuarioDaoImpl.update(usuario, true);
			}			
		}
		return insertOk;
	}

	@Override
	public boolean delete(Medico medico) {
		medicoDaoImpl = new MedicoDaoImpl();
		boolean update = false;
		if (medico != null)
			update = medicoDaoImpl.update(medico, true);
		return update;
	}

	@Override
	public boolean update(Medico medico) {
		medicoDaoImpl = new MedicoDaoImpl();
		boolean update = false;
		if (medico != null)
			update = medicoDaoImpl.update(medico, false);
		return update;
	}
	
	@Override
	public boolean update(Medico medico,Usuario usuario) {		
		boolean updateMedico = update(medico);		
		if (usuario != null && updateMedico) {		
			UsuarioDao usuarioDaoImpl = new UsuarioDaoImpl();
			updateMedico = usuarioDaoImpl.update(usuario, false);
		}
		return updateMedico;		
	}
	@Override
	public ArrayList<Medico> readAll(int estado) {
		medicoDaoImpl = new MedicoDaoImpl();
		return medicoDaoImpl.readAll(estado);
	}

	@Override
	public boolean exists(Medico medico) {
		medicoDaoImpl = new MedicoDaoImpl();
		boolean estado = false;
		if (medico.getDni() > 0) {
			Medico m = medicoDaoImpl.searchMedico(medico.getDni());
			if (m != null)
				estado = true;
		}
		return estado;
	}
	
	public Medico searchUsuario(Usuario usuario) {
		medicoDaoImpl = new MedicoDaoImpl();
		Medico med = null;		
		   med = medicoDaoImpl.searchMedicoUsuario(usuario.getIdUsuario());  
			 		
		return med;
	}
	
	public Medico searchDni(int dni) {		
		medicoDaoImpl = new MedicoDaoImpl();
		Medico med = null;
		if(dni > 0)
		{
		   med = medicoDaoImpl.searchMedico(dni);		  
			 
		}
		return med;
	}

	@Override
	public boolean medicoAtiende(Medico medico, DayOfWeek dia, int hora) {
		Jornada jornada = medico.getJornada();
		 switch (dia) {
	         case MONDAY:
	             return horaEnRango(hora, jornada.getInicioLunes(), jornada.getFinLunes());
	         case TUESDAY:
	        	 return horaEnRango(hora, jornada.getInicioMartes(), jornada.getFinMartes());
	         case WEDNESDAY:
	        	 return horaEnRango(hora, jornada.getInicioMiercoles(), jornada.getFinMiercoles());
	         case THURSDAY:
	        	 return horaEnRango(hora, jornada.getInicioJueves(), jornada.getFinJueves());
	         case FRIDAY:
	        	 return horaEnRango(hora, jornada.getInicioViernes(), jornada.getFinViernes());
	         case SATURDAY:
	        	 return horaEnRango(hora, jornada.getInicioSabado(), jornada.getFinSabado());
	         case SUNDAY:
	        	 return horaEnRango(hora, jornada.getInicioDomingo(), jornada.getFinDomingo());
	         default:
	             return false;
		 }
	}
		 
	 private boolean horaEnRango(int hora, int inicio, int fin) {
		 return hora >= inicio && hora < fin;
	 }

}
