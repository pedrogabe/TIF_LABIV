package negocioImpl;

import java.time.DayOfWeek;
import java.util.ArrayList;

import entidad.Turno;
import dao.TurnoDao;
import daoImpl.TurnoDaoImpl;
import negocio.*;

public class TurnosNegocioImpl implements TurnosNegocio {
	private TurnoDao tdao = new TurnoDaoImpl();
	private MedicoNegocio mneg;
	private PacienteNegocio pneg; 
	
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
		if(turno.getHora() > 23 || turno.getHora() < 0)
			return false;
		if(!mneg.exists(turno.getMedico())) 
			return false;
		if(!pneg.exists(turno.getPaciente()))
			return false;
		return true;
	}
	

}
