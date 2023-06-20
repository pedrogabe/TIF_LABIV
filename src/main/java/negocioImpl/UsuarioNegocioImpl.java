package negocioImpl;

import java.util.ArrayList;

import dao.UsuarioDao;
import daoImpl.UsuarioDaoImpl;
import entidad.Usuario;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio{

	UsuarioDao usuarioDaoImp = null;
	
	@Override
	public boolean insert(Usuario usuario) {
		usuarioDaoImp = new UsuarioDaoImpl();
		boolean insertado = false;
		if (usuario.getUserLogin().trim() != "" && usuario.getPassword().trim() != ""
				&& usuario.getIdPerfil() > 0) {
			insertado = usuarioDaoImp.insert(usuario);
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
		// TODO Auto-generated method stub
		return false;
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
		usuarioDaoImp = new UsuarioDaoImpl();
		
		return usuarioDaoImp.selectMaxId();
	}

	@Override
	public Usuario getUsuario(int idUsuario) {
		usuarioDaoImp = new UsuarioDaoImpl();
		
		return usuarioDaoImp.searchUsuario(idUsuario);
	}

}
