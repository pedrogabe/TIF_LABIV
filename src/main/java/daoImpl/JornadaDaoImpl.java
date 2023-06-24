package daoImpl;

import java.util.ArrayList;
import java.sql.*;
import dao.JornadaDao;
import entidad.Jornada;
import entidad.Paciente;

public class JornadaDaoImpl implements JornadaDao {
	private static final String READALL = "SELECT j.IdJornada, j.Descripcion, j.Estado,"
			+ " j.InicioLunes, j.FinLunes, j.InicioMartes, j.FinMartes, j.InicioMiercoles, j.FinMiercoles,"
			+ " j.InicioJueves, j.FinJueves, j.InicioViernes, j.FinViernes, " + 
			" j.InicioSabado, j.FinSabado, j.InicioDomingo, j.FinDomingo FROM clinica_medica.jornadas j ";
	
	@Override
	public ArrayList<Jornada> readAll(int estado) {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Jornada> jornadas = new ArrayList<Jornada>();
		Conexion conexion = Conexion.getConexion();
		try {
			String query = READALL;
			
			if (estado == 0)
				query += " WHERE j.estado = 0";
			else if (estado > 0)
				query += " WHERE j.estado = 1";			
			
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				jornadas.add(getJornada(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jornadas;
	}

	private Jornada getJornada(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("IdJornada");
		String descripcion = resultSet.getString("Descripcion");
		int inicioLunes = resultSet.getInt("InicioLunes");
		int finLunes = resultSet.getInt("FinLunes");
		int inicioMartes = resultSet.getInt("InicioMartes");
		int finMartes = resultSet.getInt("FinMartes") ;
		int inicioMiercoles = resultSet.getInt("InicioMiercoles");
		int finMiercoles = resultSet.getInt("FinMiercoles");
		int inicioJueves = resultSet.getInt("InicioJueves");
		int finJueves = resultSet.getInt("FinJueves");
		int inicioViernes = resultSet.getInt("InicioViernes") ;
		int finViernes = resultSet.getInt("FinViernes");
		int inicioSabado = resultSet.getInt("InicioSabado");
		int finSabado = resultSet.getInt("FinSabado");
		int inicioDomingo = resultSet.getInt("InicioDomingo");
		int finDomingo = resultSet.getInt("FinDomingo");
		return new Jornada(id, descripcion, inicioLunes, finLunes, inicioMartes, finMartes, inicioMiercoles, finMiercoles, inicioJueves, finJueves, inicioViernes, finViernes, inicioSabado, finSabado, inicioDomingo, finDomingo);
	}
	
}
