package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import dao.PacienteDao;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Paciente;
import entidad.Provincia;

public class PacienteDaoImpl implements PacienteDao {

	private static final String INSERT = "INSERT INTO clinica_medica.pacientes (Dni, Nombre, Apellido, Sexo, idNacionalidad, FechaNacimiento, "
			+ "Direccion, idLocalidad, idProvincia, CorreoElectronico, Telefono, Estado) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String SELECT_COUNT = "SELECT COUNT(*) FROM clinica_medica.pacientes WHERE Dni = ?";
	private static final String CAMBIA_ESTADO = "UPDATE clinica_medica.pacientes SET Estado = ? WHERE Dni = ?";
	private static final String UPDATE = "UPDATE clinica_medica.pacientes SET Nombre = ?, Apellido = ?, Sexo = ?, IdNacionalidad = ?, "
			+ "FechaNacimiento = ?, Direccion = ?, IdLocalidad = ?, IdProvincia = ?, CorreoElectronico = ?, Telefono = ?, Estado = ? WHERE Dni = ?";
	private static final String READALL = "SELECT p.Id, p.Dni, p.Nombre, p.Apellido, p.Sexo, p.IdNacionalidad, n.Nacionalidad, p.FechaNacimiento, p.Direccion, "
			+ "p.IdLocalidad, l.Localidad, p.IdProvincia, pr.Provincia, p.CorreoElectronico, p.Telefono, p.Estado "
			+ "FROM clinica_medica.pacientes p "
			+ "INNER JOIN clinica_medica.nacionalidades n ON n.IdNacionalidad = p.IdNacionalidad "
			+ "INNER JOIN clinica_medica.provincias pr ON pr.IdProvincia = p.IdProvincia "
			+ "INNER JOIN clinica_medica.localidades l ON l.IdLocalidad = p.IdLocalidad";
	private static final String SEARCH = "SELECT p.Id, p.Dni, p.Nombre, p.Apellido, p.Sexo, p.IdNacionalidad, n.Nacionalidad, p.FechaNacimiento, p.Direccion, "
			+ "p.IdLocalidad, l.Localidad, p.IdProvincia, pr.Provincia, p.CorreoElectronico, p.Telefono, p.Estado "
			+ "FROM clinica_medica.pacientes p "
			+ "INNER JOIN clinica_medica.nacionalidades n ON n.IdNacionalidad = p.IdNacionalidad "
			+ "INNER JOIN clinica_medica.provincias pr ON pr.IdProvincia = p.IdProvincia "
			+ "INNER JOIN clinica_medica.localidades l ON l.IdLocalidad = p.IdLocalidad WHERE p.Dni = ?";

	@Override
	public boolean insert(Paciente paciente) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(INSERT);
			statement.setInt(1, paciente.getDni());
			statement.setString(2, paciente.getNombre());
			statement.setString(3, paciente.getApellido());
			statement.setString(4, paciente.getSexo());
			statement.setInt(5, paciente.getNacionalidad().getIdNacionalidad());
			statement.setString(6, paciente.getFechaNacimiento());
			statement.setString(7, paciente.getDireccion());
			statement.setInt(8, paciente.getLocalidad().getIdLocalidad());
			statement.setInt(9, paciente.getProvincia().getIdProvincia());
			statement.setString(10, paciente.geteMail());
			statement.setString(11, paciente.getTelefono());
			statement.setInt(12, 1);
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return isInsertExitoso;
	}

	@Override
	public int selectCount(Paciente paciente) {
		PreparedStatement statement;
		ResultSet resultSet;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		int count = 0;
		try {
			statement = conexion.prepareStatement(SELECT_COUNT);
			statement.setInt(1, paciente.getDni());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public ArrayList<Paciente> readAll(int estado) {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
		Conexion conexion = Conexion.getConexion();
		try {
			String query = READALL;
			
			if (estado == 0)
				query += " WHERE p.estado = 0";
			else if (estado > 0)
				query += " WHERE p.estado = 1";			
			
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				pacientes.add(getPaciente(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pacientes;
	}

	private Paciente getPaciente(ResultSet resultSet) throws SQLException {
		int dni = resultSet.getInt("Dni");
		String nombre = resultSet.getString("Nombre");
		String apellido = resultSet.getString("Apellido");
		String sexo = resultSet.getString("Sexo");
		int idNacionalidad = resultSet.getInt("IdNacionalidad");
		String nacionalidad = resultSet.getString("Nacionalidad");
		String fechaNac = resultSet.getString("FechaNacimiento");
		String direccion = resultSet.getString("Direccion");
		int idLocalidad = resultSet.getInt("IdLocalidad");
		String localidad = resultSet.getString("Localidad");
		int idProvincia = resultSet.getInt("IdProvincia");
		String provincia = resultSet.getString("Provincia");
		String correoElec = resultSet.getString("CorreoElectronico");
		String telefono = resultSet.getString("Telefono");
		int estado = resultSet.getInt("Estado");
				
		
		return new Paciente(dni, nombre, apellido, sexo, new Nacionalidad(idNacionalidad,nacionalidad), fechaNac, direccion, 
				new Localidad(idLocalidad, localidad, new Provincia(idProvincia, provincia)), new Provincia(idProvincia, provincia), correoElec, telefono, estado);
	}

	@Override
	public boolean update(Paciente paciente, boolean esBaja) {
		if (esBaja)
			return bajaPaciente(paciente);
		else
			return actualizaPaciente(paciente);
	}

	private boolean bajaPaciente(Paciente paciente) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean estadoCambiado = false;
		try {
			statement = conexion.prepareStatement(CAMBIA_ESTADO);
			statement.setInt(1, 0);
			statement.setInt(2, paciente.getDni());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				estadoCambiado = true;
			}
		} catch (SQLException e) {
		}
		return estadoCambiado;
	}

	private boolean actualizaPaciente(Paciente paciente) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean actualizado = false;
		try {
			statement = conexion.prepareStatement(UPDATE);
			statement.setString(1, paciente.getNombre());
			statement.setString(2, paciente.getApellido());
			statement.setString(3, paciente.getSexo());
			statement.setInt(4, paciente.getNacionalidad().getIdNacionalidad());
			statement.setString(5, paciente.getFechaNacimiento());
			statement.setString(6, paciente.getDireccion());
			statement.setInt(7, paciente.getLocalidad().getIdLocalidad());
			statement.setInt(8, paciente.getProvincia().getIdProvincia());
			statement.setString(9, paciente.geteMail());
			statement.setString(10, paciente.getTelefono());
			statement.setInt(11, paciente.getEstado());
			statement.setInt(12, paciente.getDni());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				actualizado = true;
			}
		} catch (SQLException e) {
		}
		return actualizado;
	}

	@Override
	public Paciente searchPaciente(int dni) {
		PreparedStatement statement;
		ResultSet resultSet;
		Paciente paciente = null;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(SEARCH);
			statement.setInt(1, dni);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				paciente = getPaciente(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paciente;
	}

}
