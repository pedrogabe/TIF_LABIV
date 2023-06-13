package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PacienteDao;
import entidad.Paciente;

public class PacienteDaoImpl implements PacienteDao {

	private static final String INSERT = "INSERT INTO clinica_medica.pacientes (Dni, Nombre, Apellido, Sexo, Nacionalidad, FechaNacimiento, "
			+ "Direccion, Localidad, Provincia, CorreoElectronico, Telefono, Estado) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String SELECT_COUNT = "SELECT COUNT(*) FROM clinica_medica.pacientes WHERE Dni = ?";
	private static final String CAMBIA_ESTADO = "UPDATE clinica_medica.pacientes SET Estado = ? WHERE Dni = ?";
	private static final String UPDATE = "UPDATE clinica_medica.pacientes SET Nombre = ?, Apellido = ?, Sexo = ?, Nacionalidad = ?, "
			+ "FechaNacimiento = ?, Direccion = ?, Localidad = ?, Provincia = ?, CorreoElectronico = ?, Telefono = ?, Estado = ? WHERE Dni = ?";
	private static final String READALL = "SELECT Dni, Nombre, Apellido, Sexo, Nacionalidad, FechaNacimiento, "
			+ "Direccion, Localidad, Provincia, CorreoElectronico, Telefono, Estado FROM clinica_medica.pacientes";

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
			statement.setString(5, paciente.getNacionalidad());
			statement.setString(6, paciente.getFechaNacimiento());
			statement.setString(7, paciente.getDireccion());
			statement.setString(8, paciente.getLocalidad());
			statement.setString(9, paciente.getProvincia());
			statement.setString(10, paciente.geteMail());
			statement.setString(11, paciente.getTelefono());
			statement.setInt(12, paciente.getEstado());
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
	public List<Paciente> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(READALL);
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
		int id = resultSet.getInt("id");
		int dni = resultSet.getInt("Dni");
		String nombre = resultSet.getString("Nombre");
		String apellido = resultSet.getString("Apellido");
		String sexo = resultSet.getString("Sexo");
		String nacionalidad = resultSet.getString("Nacionalidad");
		String fechaNac = resultSet.getString("FechaNacimiento");
		String direccion = resultSet.getString("Direccion");
		String localidad = resultSet.getString("Localidad");
		String provincia = resultSet.getString("Provincia");
		String correoElec = resultSet.getString("CorreoElectronico");
		String telefono = resultSet.getString("Telefono");
		int estado = resultSet.getInt("Estado");
		return new Paciente(id, dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia,
				correoElec, telefono, estado);
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
			statement.setInt(1, paciente.getEstado());
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
			statement.setString(4, paciente.getNacionalidad());
			statement.setString(5, paciente.getFechaNacimiento());
			statement.setString(6, paciente.getDireccion());
			statement.setString(7, paciente.getLocalidad());
			statement.setString(8, paciente.getProvincia());
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

}
