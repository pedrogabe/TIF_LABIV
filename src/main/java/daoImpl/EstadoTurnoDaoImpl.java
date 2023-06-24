package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.EstadoTurnoDao;
import entidad.EstadoTurno;

public class EstadoTurnoDaoImpl implements EstadoTurnoDao{

	private static final String READALL = "SELECT et.IdEstadoTurno, et.Descripcion FROM clinica_medica.estadosturno et";
	@Override
	public ArrayList<EstadoTurno> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<EstadoTurno> estadoTurnos = new ArrayList<EstadoTurno>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(READALL);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				estadoTurnos.add(getEstadoTurno(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estadoTurnos;
	}

	private EstadoTurno getEstadoTurno(ResultSet resultSet) throws SQLException {
		int idEstadoTurno = resultSet.getInt("IdEstadoTurno");
		String estadoTurno = resultSet.getString("Descripcion");

		return new EstadoTurno(idEstadoTurno, estadoTurno);
	}
	
}
