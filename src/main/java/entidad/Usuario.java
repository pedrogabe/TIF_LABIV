package entidad;

public class Usuario {
	
	private int IdUsuario;
	private String UserLogin;
	private String Password;
	private int IdPerfil;
	
	public Usuario(int idUsuario, String userLogin, String password, int idPerfil) {
		IdUsuario = idUsuario;
		UserLogin = userLogin;
		Password = password;
		IdPerfil = idPerfil;
	}
	public final static int ROL_MEDICO=2;
	public Usuario() {}
	
	public int getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}
	public String getUserLogin() {
		return UserLogin;
	}
	public void setUserLogin(String userLogin) {
		UserLogin = userLogin;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public int getIdPerfil() {
		return IdPerfil;
	}
	public void setIdPerfil(int idPerfil) {
		IdPerfil = idPerfil;
	}	

	@Override
	public String toString() {
		return "Usuario [IdUsuario=" + IdUsuario + ", UserLogin=" + UserLogin + ", Password=" + Password + ", IdPerfil="
				+ IdPerfil + "]";
	}
}
