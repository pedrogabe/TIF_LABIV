package negocioImpl;

import java.util.ArrayList;

import dao.JornadaDao;
import daoImpl.JornadaDaoImpl;
import entidad.Jornada;
import negocio.JornadaNegocio;

public class JornadaNegocioImpl implements JornadaNegocio {
	private JornadaDao jdao = new JornadaDaoImpl();
	@Override
	public ArrayList<Jornada> readAll(int estado) {
		return jdao.readAll(estado);
	}

}
