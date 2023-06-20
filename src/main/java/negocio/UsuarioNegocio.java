package negocio;

import java.util.ArrayList;

import entidad.Usuario;

public interface UsuarioNegocio {
	public boolean insert(Usuario usuario);
	public boolean delete(Usuario usuario);
	public boolean update(Usuario usuario);
	public ArrayList<Usuario> readAll(int estado);
	public boolean exists(Usuario usuario);
	public int maxId();
}