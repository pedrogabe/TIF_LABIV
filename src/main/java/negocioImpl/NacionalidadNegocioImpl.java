package negocioImpl;

import java.util.ArrayList;

import dao.NacionalidadDao;
import daoImpl.NacionalidadDaoImpl;
import entidad.Nacionalidad;
import negocio.NacionalidadNegocio;

public class NacionalidadNegocioImpl implements NacionalidadNegocio {

	@Override
	public ArrayList<Nacionalidad> readAll() {
		NacionalidadDao nacDao = new NacionalidadDaoImpl();
		return nacDao.readAll();
	}

}
