package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.MedicoDao;
import dao.RepMedicoDao;
import entidad.ReporteMedico;


public class RepMedicoDaoImpl implements RepMedicoDao {
	
	private final String TOTAL_TURNOS_X_MES = "SELECT COUNT(*) as CantidadTurnos FROM turnos Where MONTH(FechaReserva) = ?";
	private final String TOTAL_TURNOS_X_MEDICOS = "SELECT m.Dni, COUNT(t.IdTurno) as CantidadTurnos FROM turnos t "
												+ "INNER JOIN medicos m ON t.IdMedico = m.Id Where MONTH(t.FechaReserva)= ? GROUP BY m.dni";
	
	private int cantidadTurnosMes;
	
	@Override
	public ArrayList <ReporteMedico> searchReporteMes(int mes) {

		cantidadTurnosMes = buscarCantidadTurnosMes(mes);

		return readCantTurnosMedico(mes);
	}
	

	private int buscarCantidadTurnosMes(int mes) {
		int totalTurnosMes = 0;

		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();

		try {

			statement = conexion.getSQLConexion().prepareStatement(TOTAL_TURNOS_X_MES);
			statement.setInt(1,mes);
			
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				totalTurnosMes = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return totalTurnosMes;

	}
	
	private ArrayList<ReporteMedico> readCantTurnosMedico(int mes) {
		PreparedStatement statement;
		ResultSet resultSet;

		Conexion conexion = Conexion.getConexion();
		
		ArrayList<ReporteMedico> reporteMedicos = new ArrayList<ReporteMedico>();
		
		
		try {
			statement = conexion.getSQLConexion().prepareStatement(TOTAL_TURNOS_X_MEDICOS);
			statement.setInt(1,mes);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				reporteMedicos.add(getRepMedico(resultSet));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reporteMedicos;
	}

	
private ReporteMedico getRepMedico(ResultSet resultSet) throws SQLException {
			
		ReporteMedico reporte_medico = new ReporteMedico();
		MedicoDao medicodao = new MedicoDaoImpl();
		
		try {
			
			reporte_medico.setTotal_por_mes(cantidadTurnosMes); 
			reporte_medico.setTotal_por_medico(resultSet.getInt("CantidadTurnos"));
			
			reporte_medico.setMedico(medicodao.searchMedico(resultSet.getInt("Dni"))); 
			reporte_medico.setProcentaje_por_medico(getPorcentaje(resultSet.getInt("CantidadTurnos")));
			
		}catch(SQLException e){
			
			e.printStackTrace();
		}
		
		return reporte_medico;
	}
	
	private String getPorcentaje(int cantTurno) throws SQLException {

		double porcentaje = 0;
		String porc = "";
		if (cantidadTurnosMes > 0) {
			porcentaje = cantTurno * 100 / (double) cantidadTurnosMes;
			porc = String.format("%.2f", porcentaje);
		}
		return porc;
	}

}
