package negocioImpl;

import java.util.ArrayList;

import dao.UsuarioDao;
import daoImpl.UsuarioDaoImpl;
import entidad.Usuario;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio{

	UsuarioDao usuarioDaoImpl = null;
	
	@Override
	public boolean insert(Usuario usuario) {
		usuarioDaoImpl = new UsuarioDaoImpl();
		boolean insertado = false;
		if (usuario.getUserLogin().trim() != "" && usuario.getPassword().trim() != ""
				&& usuario.getIdPerfil() > 0) {
			insertado = usuarioDaoImpl.insert(usuario);
		}
		return insertado;
	}

	@Override
	public boolean delete(Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Usuario usuario) {
		usuarioDaoImpl = new UsuarioDaoImpl();
		boolean update = false;
		if (!usuario.getUserLogin().trim().equals("") && !usuario.getPassword().trim().equals(""))
			update = usuarioDaoImpl.update(usuario, false);		
		return update;
	}

	@Override
	public ArrayList<Usuario> readAll(int estado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int maxId() {
		usuarioDaoImpl = new UsuarioDaoImpl();
		
		return usuarioDaoImpl.selectMaxId();
	}

	@Override
	public Usuario getUsuario(int idUsuario) {
		usuarioDaoImpl = new UsuarioDaoImpl();
		
		return usuarioDaoImpl.searchUsuario(idUsuario);
	}

}
