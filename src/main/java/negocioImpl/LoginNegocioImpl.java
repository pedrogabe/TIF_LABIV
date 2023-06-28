package negocioImpl;


import dao.LoginDao;
import daoImpl.LoginDaoImpl;
import entidad.Usuario;
import negocio.LoginNegocio;

public class LoginNegocioImpl implements LoginNegocio {

	@Override
	public Usuario ValidarUsuario(String usuario, String password) {
		LoginDao s = new LoginDaoImpl();
		Usuario user = s.readUser(usuario,password);
		if(user != null && user.getUserLogin().equals(usuario) && user.getPassword().equals(password))
			return s.readUser(usuario,password);
		else
			return null;
	}
	
	
}