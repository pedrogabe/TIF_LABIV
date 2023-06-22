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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Turno turno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Turno turno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Turno> readAll(int estado) {
		return tdao.readAll(estado);
	}

	@Override
	public boolean exists(Turno turno) {
		// TODO Auto-generated method stub
		return false;
	}

}
