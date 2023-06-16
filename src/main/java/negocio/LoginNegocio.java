package negocio;


import entidad.Usuario;

public interface LoginNegocio {
	
	public Usuario ValidarUsuario(String usuario, String password);
			
}