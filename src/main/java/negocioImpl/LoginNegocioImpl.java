package negocioImpl;

import dao.LoginDao;
import daoImpl.LoginDaoImpl;
import entidad.Usuario;
import negocio.LoginNegocio;

public class LoginNegocioImpl implements LoginNegocio {

	@Override
	public Usuario ValidarUsuario(String usuario, String password) {
		LoginDao s = new LoginDaoImpl();
		
		return s.readUser(usuario,password);
	}
	
	
}
