package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.LoginDao;
import entidad.Usuario;

public class LoginDaoImpl implements LoginDao {

	private static final String SELECT_USER = "select * from clinica_medica.usuarios u left join clinica_medica.medicos m"
			+ " ON u.IdUsuario = m.IdUsuario where UserLogin=? and Password=? and (IdPerfil=1 or m.estado=1)";

	@Override
	public Usuario readUser(String usuario, String password) {
		PreparedStatement statement;
		ResultSet resultSet;
		Usuario usuar = null;
		Conexion conexion = Conexion.getConexion();

		try {
			statement = conexion.getSQLConexion().prepareStatement(SELECT_USER);
			statement.setString(1, usuario.trim());
			statement.setString(2, password.trim());

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				usuar = getUsuario(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuar;
	}

	private Usuario getUsuario(ResultSet resultSet) throws SQLException {
		int IdUsuario = resultSet.getInt("IdUsuario");
		String UserLogin = resultSet.getString("UserLogin");
		String Password = resultSet.getString("Password");
		int IdPerfil = resultSet.getInt("IdPerfil");

		return new Usuario(IdUsuario, UserLogin, Password, IdPerfil);
	}

}