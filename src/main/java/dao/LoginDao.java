package dao;


import entidad.Usuario;

public interface LoginDao {

	public Usuario readUser(String usuario,String password);
}


