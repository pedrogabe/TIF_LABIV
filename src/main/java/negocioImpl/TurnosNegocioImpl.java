package negocioImpl;

import java.util.ArrayList;

import entidad.Turno;
import dao.TurnoDao;
import daoImpl.TurnoDaoImpl;
import negocio.TurnosNegocio;

public class TurnosNegocioImpl implements TurnosNegocio {
	private TurnoDao tdao = new TurnoDaoImpl();
	
	@Override
	public boolean insert(Turno turno) {
		if(turno.getHora() > 23 || turno.getHora() < 0)
			return false;
		return false;
	}

	@Override
	public boolean delete(Turno turno) {
		return tdao.update(turno, true);
	}

	@Override
	public boolean update(Turno turno) {
		if(turno.getHora() > 23 || turno.getHora() < 0)
			return false;
		return tdao.update(turno, false);
	}

	@Override
	public ArrayList<Turno> readAll(int estado) {
		return tdao.readAll(estado);
	}

	@Override
	public boolean exists(Turno turno) {
		return tdao.searchTurno(turno.getIdTurno()) != null;
	}
	

}
