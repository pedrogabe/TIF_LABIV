package negocioImpl;

import java.util.ArrayList;

import dao.*;
import daoImpl.*;
import entidad.EstadoTurno;
import negocio.EstadoTurnoNegocio;

public class EstadoTurnoNegocioImpl implements EstadoTurnoNegocio{

	@Override
	public ArrayList<EstadoTurno> readAll() {
		EstadoTurnoDao etDao = new EstadoTurnoDaoImpl();
		return etDao.readAll();
	}
}
