package dao;

import java.util.ArrayList;

import entidad.Usuario;

public interface UsuarioDao {
	public boolean insert(Usuario usuario);
	public boolean update(Usuario usuario, boolean esBaja);
	public ArrayList<Usuario> readAll(int usuario);
	public Usuario searchUsuario(String userLogin);
	public int selectCount(Usuario usuario);

}
