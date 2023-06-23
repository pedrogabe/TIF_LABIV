package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.EspecialidadDao;
import entidad.Especialidad;

public class EspecialidadDaoImpl implements EspecialidadDao {

	private static final String READALL = "SELECT e.IdEspecialidad, e.Descripcion FROM clinica_medica.especialidades e";
	@Override
	public ArrayList<Especialidad> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Especialidad> especialidades = new ArrayList<Especialidad>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(READALL);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				especialidades.add(getEspecialidad(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return especialidades;
	}

	private Especialidad getEspecialidad(ResultSet resultSet) throws SQLException {
		int idEspecialidad = resultSet.getInt("IdEspecialidad");
		String especialidad = resultSet.getString("Descripcion");

		return new Especialidad(idEspecialidad, especialidad);
	}
}
