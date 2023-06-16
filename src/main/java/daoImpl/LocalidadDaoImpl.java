package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.LocalidadDao;
import entidad.Localidad;
import entidad.Provincia;

public class LocalidadDaoImpl implements LocalidadDao {

	private static final String READALL = "SELECT l.IdLocalidad, l.Localidad, n.IdProvincia, n.Provincia "
			+ "FROM clinica_medica.localidades l INNER JOIN clinica_medica.provincias n ON n.IdProvincia = l.IdProvincia";

	@Override
	public List<Localidad> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Localidad> localidades = new ArrayList<Localidad>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(READALL);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				localidades.add(getLocalidad(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return localidades;
	}

	private Localidad getLocalidad(ResultSet resultSet) throws SQLException {
		int idLocalidad = resultSet.getInt("IdLocalidad");
		String localidad = resultSet.getString("Localidad");
		int idProvincia = resultSet.getInt("IdNacionalidad");
		String provincia = resultSet.getString("Nacionalidad");

		return new Localidad(idLocalidad, localidad, new Provincia(idProvincia, provincia));
	}

}
