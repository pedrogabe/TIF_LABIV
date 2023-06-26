package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.text.*;

import dao.RepEspecialidadDao;
import entidad.ReporteEspecialidad;

public class RepEspecialidadDaoImpl implements RepEspecialidadDao {

	private final String TOTAL_TURNO_MES = "SELECT count(t.IdTurno) countTurnosMes FROM turnos t WHERE date(t.FechaReserva) BETWEEN ? AND last_day(?)";
	private final String CANTIDAD_TURNO_MES = "SELECT count(t.IdTurno) 'CantTurnos', e.Descripcion FROM turnos t "
			+ "INNER JOIN medicos m on t.IdMedico = m.Id "
			+ "INNER JOIN especialidades e on m.IdEspecialidad = e.IdEspecialidad "
			+ "WHERE t.FechaReserva BETWEEN ? AND last_day(?) GROUP BY e.Descripcion ORDER BY 'CantTurnos' DESC";

	//private final String READALL_ESPECIALIDADES = "SELECT e.IdEspecialidad, e.Descripcion FROM clinica_medica.especialidades e";

	private int cantidadTurnosMes;

	@Override
	public ReporteEspecialidad searchReporteMes(int mes) {

		DateFormat formateador = new SimpleDateFormat("dd/M/yy");
		Date fecha = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		String patternFecha = "01/" + darCadenaMes(mes) + "/" + calendar.get(Calendar.YEAR);

		try {
			fecha = formateador.parse(patternFecha);
		} catch (Exception e) {
			e.printStackTrace();

		}

		cantidadTurnosMes = buscarCantidadTurnosMes(fecha, mes);

		return readCantTurnos(fecha, mes);
	}

	private String darCadenaMes(int mes) {
		String sMes = "" + mes;
		if (sMes.length() == 1)
			sMes = "0" + sMes;
		return sMes;
	}

	private int buscarCantidadTurnosMes(Date fecha, int mes) {
		int totalTurnosMes = 0;

		PreparedStatement statement;
		ResultSet resultSet;
		Connection conexion = Conexion.getConexion().getSQLConexion();

		try {

			statement = conexion.prepareStatement(TOTAL_TURNO_MES);
			statement.setDate(1, new java.sql.Date(fecha.getTime()));
			statement.setDate(2, new java.sql.Date(fecha.getTime()));
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				totalTurnosMes = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return totalTurnosMes;

	}

	public ReporteEspecialidad readCantTurnos(Date fecha, int mes) {
		PreparedStatement statement;
		ResultSet resultSet;
		ReporteEspecialidad repoEsp = new ReporteEspecialidad();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(CANTIDAD_TURNO_MES);			
			statement.setDate(1, new java.sql.Date(fecha.getTime()));
			statement.setDate(2, new java.sql.Date(fecha.getTime()));
			resultSet = statement.executeQuery();
			//Titulos de las columnas del Reporte a visualizar total 3
			repoEsp.addColumna("Especialidad"); 
			repoEsp.addColumna("Total Turnos");			
			repoEsp.addColumna("Porcentaje");
			
			while (resultSet.next()) {
				int cantTurno = resultSet.getInt("CantTurnos");
				repoEsp.addValor(resultSet.getString("Descripcion"));
				repoEsp.addValor("" + cantTurno);				
				repoEsp.addValor(getPorcentaje(resultSet, cantTurno));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return repoEsp;
	}

	private String getPorcentaje(ResultSet resultSet, int cantTurno) throws SQLException {
		
		double porcentaje = 0;
		String porc = "";
		if (cantidadTurnosMes > 0)
		{
			porcentaje = cantTurno * 100 /(double) cantidadTurnosMes;
			porc = String.format("%.2f", porcentaje);
		}
		return porc + " %";
	}

}
