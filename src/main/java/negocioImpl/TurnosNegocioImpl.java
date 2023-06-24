package negocioImpl;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.sql.Date;
import entidad.Medico;
import entidad.Turno;
import dao.TurnoDao;
import daoImpl.TurnoDaoImpl;
import negocio.*;

public class TurnosNegocioImpl implements TurnosNegocio {
	private TurnoDao tdao = new TurnoDaoImpl();
	private MedicoNegocio mneg;
	private PacienteNegocio pneg; 

	public TurnosNegocioImpl() {
	}
	
	public TurnosNegocioImpl(MedicoNegocio mneg, PacienteNegocio pneg) {
		this.mneg = mneg;
		this.pneg = pneg;
	}
	
	@Override
	public boolean insert(Turno turno) {
		if(turnoValido(turno))
			return tdao.insert(turno);
		return false;
	}

	@Override
	public boolean delete(Turno turno) {
		return tdao.update(turno, true);
	}

	@Override
	public boolean update(Turno turno) {
		if(turnoValido(turno))
			return tdao.update(turno, false);
		return false;
	}

	@Override
	public ArrayList<Turno> readAll(int estado) {
		return tdao.readAll(estado);
	}

	@Override
	public boolean exists(Turno turno) {
		return tdao.searchTurno(turno.getIdTurno()) != null;
	}
	
	public boolean turnoValido(Turno turno) {
		if(!fechaYHoraValida(turno))
			return false;
		if(!mneg.exists(turno.getMedico())) 
			return false;
		if(!pneg.exists(turno.getPaciente()))
			return false;
		return true;
	}
	
	private boolean fechaYHoraValida(Turno turno) {
		if(turno.getHora() > 23 || turno.getHora() < 0)
			return false;
		if(!medicoAtiende(turno))
			return false;
		if(turnoTomado(turno))
			return false;
		return true;
	}
	
	@SuppressWarnings("deprecation")
	private boolean medicoAtiende(Turno turno) {
		Date fecha = turno.getFechaReserva();
		int d = fecha.getDay();
        DayOfWeek dia = DayOfWeek.of(d == 0 ? 7 : d);
        return mneg.medicoAtiende(turno.getMedico(), dia, turno.getHora());
	}
	
	private boolean turnoTomado(Turno turno) {
		int dniMed = turno.getMedico().getDni();
		int dniPac = turno.getPaciente().getDni();
		Date fecha = turno.getFechaReserva();
		
		ArrayList<Turno> tsDiaHorario = tdao.searchTurnosDiaHorario(fecha, turno.getHora());
		for(Turno tomado : tsDiaHorario) {
			if(dniMed == tomado.getMedico().getDni())
				return true;
			if(dniPac == tomado.getPaciente().getDni())
				return true;
		}
		
		return false;
	}
	
	public ArrayList<Turno> readTurnosMedico(Medico medico) {
		return tdao.searchTurnosMedico(medico.getDni());
	}
	

}
