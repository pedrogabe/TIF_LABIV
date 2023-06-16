package negocioImpl;

import java.util.ArrayList;

import entidad.Provincia;
import negocio.ProvinciaNegocio;
import dao.ProvinciaDao;
import daoImpl.ProvinciaDaoImpl;

public class ProvinciaNegocioImpl implements ProvinciaNegocio  {

	@Override
	public ArrayList<Provincia> readAll() {
		// TODO 
		ProvinciaDao provDao = new ProvinciaDaoImpl();
		
		return provDao.readAll();
	}

}
