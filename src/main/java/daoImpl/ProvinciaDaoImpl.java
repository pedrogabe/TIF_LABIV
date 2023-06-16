package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProvinciaDao;
import entidad.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao {

	private static final String READALL = "SELECT n.IdProvincia, n.Provincia"
			+ "FROM clinica_medica.provincias n";

	@Override
	public List<Provincia> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Provincia> provincias = new ArrayList<Provincia>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(READALL);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				provincias.add(getProvincia(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return provincias;
	}

	private Provincia getProvincia(ResultSet resultSet) throws SQLException {
		int idProvincia = resultSet.getInt("IdNacionalidad");
		String provincia = resultSet.getString("Nacionalidad");

		return new Provincia(idProvincia, provincia);
	}

}
