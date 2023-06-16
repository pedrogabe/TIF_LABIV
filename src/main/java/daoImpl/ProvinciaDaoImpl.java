package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import dao.ProvinciaDao;
import entidad.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao {
	private static final String READALL = "SELECT * FROM clinica_medica.provincias";
	
	@Override
	public ArrayList<Provincia> readAll() {
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
		int idProvincia = resultSet.getInt("idProvincia");
		String provincia = resultSet.getString("provincia");

		return new Provincia(idProvincia, provincia);
	}

}
