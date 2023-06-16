package negocioImpl;

import java.util.ArrayList;

import entidad.Localidad;
import negocio.LocalidadNegocio;
import dao.LocalidadDao;
import daoImpl.LocalidadDaoImpl;

public class LocalidadNegocioImpl implements LocalidadNegocio  {

	@Override
	public ArrayList<Localidad> readAll() {
		// TODO 
		LocalidadDao locDao = new LocalidadDaoImpl();
		
		return locDao.readAll();
	}

}
