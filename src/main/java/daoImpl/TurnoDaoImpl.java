package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.TurnoDao;
import entidad.EstadoTurno;
import entidad.Localidad;
import entidad.Medico;
import entidad.Nacionalidad;
import entidad.Paciente;
import entidad.Provincia;
import entidad.Turno;

public class TurnoDaoImpl implements TurnoDao {
	
	private static final String READALL = "" + 
			" SELECT t.IdTurno, t.FechaReserva, t.Observacion, t.IdTurnoEstado, t.Hora, t.Estado, et.IdEstadoTurno as TurnoEstado," + 
			" p.Id as IdPac, p.Dni as DniPac, p.Nombre as NombrePac, p.Apellido as ApellidoPac, p.Sexo as SexoPac, p.IdNacionalidad as IdNacionalidadPac, np.Nacionalidad as NacionalidadPac, p.FechaNacimiento as FechaNacimientoPac, p.Direccion as DireccionPac, " + 
			" p.IdLocalidad as IdLocalidadPac, lp.Localidad as LocalidadPac, p.IdProvincia as IdProvinciaPac, pp.Provincia as ProvinciaPac, p.CorreoElectronico as CorreoElectronicoPac, p.Telefono as TelefonoPac, p.Estado as EstadoPac," + 
			" m.Id as IdMed, m.IdUsuario as IdUsuarioMed, m.Dni as DniMed, m.Nombre as NombreMed, m.Apellido as ApellidoMed, m.Sexo as SexoMed, m.IdNacionalidad as IdNacionalidadMed, nm.Nacionalidad as NacionalidadMed, " + 
			" m.FechaNacimiento as FechaNacimientoMed, m.Direccion as DireccionMed, m.IdLocalidad as IdLocalidadMed, lm.Localidad as LocalidadMed, m.IdProvincia as IdProvinciaMed, pm.Provincia as ProvinciaMed, m.CorreoElectronico as CorreoElectronicoMed, m.Telefono as TelefonoMed, m.Estado as EstadoMed" + 
			" FROM clinica_medica.turnos t" + 
			" INNER JOIN clinica_medica.estadosturno et ON t.IdTurnoEstado = et.IdEstadoTurno" + 
			" INNER JOIN clinica_medica.pacientes p ON t.IdPaciente = p.Id" + 
			" INNER JOIN clinica_medica.nacionalidades np ON np.IdNacionalidad = p.IdNacionalidad " + 
			" INNER JOIN clinica_medica.provincias pp ON pp.IdProvincia = p.IdProvincia " + 
			" INNER JOIN clinica_medica.localidades lp ON lp.IdLocalidad = p.IdLocalidad" +  
			" INNER JOIN clinica_medica.medicos m" + 
			" INNER JOIN clinica_medica.nacionalidades nm ON nm.IdNacionalidad = m.IdNacionalidad" + 
			" INNER JOIN clinica_medica.provincias pm ON pm.IdProvincia = m.IdProvincia " + 
			" INNER JOIN clinica_medica.localidades lm ON lm.IdLocalidad = m.IdLocalidad";

	@Override
	public boolean insert(Turno turno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Turno turno, boolean esBaja) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Turno> readAll(int estado) {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Turno> turnos = new ArrayList<Turno>();
		Conexion conexion = Conexion.getConexion();
		try {
			String query = READALL;
			
			if (estado == 0)
				query += " WHERE t.estado = 0";
			else if (estado > 0)
				query += " WHERE t.estado = 1";			
			
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				turnos.add(getTurno(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return turnos;
	}

	@Override
	public Turno searchTurno(int idTurno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCount(Turno turno) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private Turno getTurno(ResultSet resultSet) throws SQLException {
		int idTurno = resultSet.getInt("idTurno");
		Medico medico = getMedico(resultSet);
		Paciente paciente = getPaciente(resultSet);
		String fechaReserva = resultSet.getString("FechaReserva");
		String observacion = resultSet.getString("observacion");
		EstadoTurno estadoTurno = new EstadoTurno(resultSet.getInt("IdTurnoEstado"), resultSet.getString("TurnoEstado"));
		
		return new Turno(idTurno, medico, paciente, fechaReserva, observacion, estadoTurno);
	}
	
	private Medico getMedico(ResultSet resultSet) throws SQLException {
		int idUsuario = resultSet.getInt("IdUsuarioMed");
		int dni = resultSet.getInt("DniMed");
		String nombre = resultSet.getString("NombreMed");
		String apellido = resultSet.getString("ApellidoMed");
		String sexo = resultSet.getString("SexoMed");
		int idNacionalidad = resultSet.getInt("IdNacionalidadMed");
		String nacionalidad = resultSet.getString("NacionalidadMed");
		String fechaNac = resultSet.getString("FechaNacimientoMed");
		String direccion = resultSet.getString("DireccionMed");
		int idLocalidad = resultSet.getInt("IdLocalidadMed");
		String localidad = resultSet.getString("LocalidadMed");
		int idProvincia = resultSet.getInt("IdProvinciaMed");
		String provincia = resultSet.getString("ProvinciaMed");
		String correoElec = resultSet.getString("CorreoElectronicoMed");
		String telefono = resultSet.getString("TelefonoMed");
		int estado = resultSet.getInt("EstadoMed");			
		return new Medico(idUsuario, dni, nombre, apellido, sexo, new Nacionalidad(idNacionalidad,nacionalidad), fechaNac, direccion, 
				new Localidad(idLocalidad, localidad, new Provincia(idProvincia, provincia)), new Provincia(idProvincia, provincia), correoElec, telefono, estado);
	}
	
	private Paciente getPaciente(ResultSet resultSet) throws SQLException {
		int dni = resultSet.getInt("DniPac");
		String nombre = resultSet.getString("NombrePac");
		String apellido = resultSet.getString("ApellidoPac");
		String sexo = resultSet.getString("SexoPac");
		int idNacionalidad = resultSet.getInt("IdNacionalidadPac");
		String nacionalidad = resultSet.getString("NacionalidadPac");
		String fechaNac = resultSet.getString("FechaNacimientoPac");
		String direccion = resultSet.getString("DireccionPac");
		int idLocalidad = resultSet.getInt("IdLocalidadPac");
		String localidad = resultSet.getString("LocalidadPac");
		int idProvincia = resultSet.getInt("IdProvinciaPac");
		String provincia = resultSet.getString("ProvinciaPac");
		String correoElec = resultSet.getString("CorreoElectronicoPac");
		String telefono = resultSet.getString("TelefonoPac");
		int estado = resultSet.getInt("EstadoPac");
				
		
		return new Paciente(dni, nombre, apellido, sexo, new Nacionalidad(idNacionalidad,nacionalidad), fechaNac, direccion, 
				new Localidad(idLocalidad, localidad, new Provincia(idProvincia, provincia)), new Provincia(idProvincia, provincia), correoElec, telefono, estado);
	}
	

}
