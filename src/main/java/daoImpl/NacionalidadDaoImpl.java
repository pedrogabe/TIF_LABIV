package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.NacionalidadDao;
import entidad.Nacionalidad;

public class NacionalidadDaoImpl implements NacionalidadDao {
	
	private static final String READALL = "SELECT n.IdNacionalidad, n.Nacionalidad"
			+ "FROM clinica_medica.nacionalidades n";

	@Override
	public List<Nacionalidad> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Nacionalidad> nacionalidades = new ArrayList<Nacionalidad>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(READALL);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				nacionalidades.add(getNacionalidad(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nacionalidades;
	}

	private Nacionalidad getNacionalidad(ResultSet resultSet) throws SQLException {
		int idNacionalidad = resultSet.getInt("IdNacionalidad");
		String nacionalidad = resultSet.getString("Nacionalidad");

		return new Nacionalidad(idNacionalidad, nacionalidad);
	}

}
