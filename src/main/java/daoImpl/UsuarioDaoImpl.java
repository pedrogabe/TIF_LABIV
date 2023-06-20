package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.UsuarioDao;
import entidad.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {

	private static final String INSERT = "INSERT INTO clinica_medica.usuarios (UserLogin, Password, IdPerfil) VALUES (?,?,?,)";
	private static final String SELECT_COUNT = "SELECT COUNT(*) FROM clinica_medica.usuarios";
	private static final String UPDATE = "UPDATE clinica_medica.usuarios SET UserLogin = ? Password = ? WHERE IdUsuario = ?";
	 // se deben buscar los perfiles a la DB
	private static final String SEARCH = "SELECT u.IdUsuario, u.UserLogin, u.Password, u.IdPerfil FROM clinica_medica.usuarios u";

	@Override
	public boolean insert(Usuario usuario) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(INSERT);
			statement.setString(1, usuario.getUserLogin());
			statement.setString(2, usuario.getPassword());
			statement.setInt(3, usuario.getIdPerfil());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return isInsertExitoso;
	}

	@Override
	public boolean update(Usuario usuario, boolean esBaja) {
		if (esBaja)
			return bajaUsuario(usuario);
		else
			return actualizaUsuario(usuario);
	}

	private boolean actualizaUsuario(Usuario usuario) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean actualizado = false;
		try {
			statement = conexion.prepareStatement(UPDATE);
			statement.setString(1, usuario.getPassword());
			statement.setInt(2, usuario.getIdUsuario());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				actualizado = true;
			}
		} catch (SQLException e) {
		}
		return actualizado;
	}

	//TODO Ver como implementar
	private boolean bajaUsuario(Usuario usuario) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean estadoCambiado = false;
		try {
			statement = conexion.prepareStatement("");
			statement.setInt(1, 0);
			statement.setInt(2, usuario.getIdUsuario());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				estadoCambiado = true;
			}
		} catch (SQLException e) {
		}
		return estadoCambiado;
	}
	
	@Override
	public ArrayList<Usuario> readAll(int estado) {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Conexion conexion = Conexion.getConexion();
		try {
			String query = SEARCH;
			
			if (estado == 0)
				query += " WHERE p.estado = 0";
			else if (estado > 0)
				query += " WHERE p.estado = 1";			
			
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				usuarios.add(getUsuario(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	private Usuario getUsuario(ResultSet resultSet) throws SQLException {
		int idUsuario = resultSet.getInt("IdUsuario");		
		String userLogin = resultSet.getString("UserLogin");
		String password = resultSet.getString("Password");
		int idPerfil = resultSet.getInt("IdPerfil");				
		
		return new Usuario(idUsuario, userLogin, password, idPerfil);
	}
	
	@Override
	public Usuario searchUsuario(int idUsuario) {
		PreparedStatement statement;
		ResultSet resultSet;
		Usuario usuario = null;
		Conexion conexion = Conexion.getConexion();
		try {
			String query = SEARCH;
			if (idUsuario != 0)
			{
				query += " WHERE Userlogin = ?";
				statement = conexion.getSQLConexion().prepareStatement(query);
				statement.setInt(1, idUsuario);
			}
			else
			{
				statement = conexion.getSQLConexion().prepareStatement(query);				
			}			
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				usuario = getUsuario(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	@Override
	public int selectCount(Usuario usuario) {
		// TODO Auto-generated method stub
		PreparedStatement statement;
		ResultSet resultSet;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		int count = 0;
		try {
			String query = SELECT_COUNT;
			if (usuario != null) {
				String where = " WHERE UserLogin = ?";
				statement = conexion.prepareStatement(query + where);
				statement.setString(1, usuario.getUserLogin());
			} else
				statement = conexion.prepareStatement(query);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
