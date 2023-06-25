package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.MedicoDao;
import entidad.Especialidad;
import entidad.Jornada;
import entidad.Localidad;
import entidad.Medico;
import entidad.Nacionalidad;
import entidad.Provincia;

public class MedicoDaoImpl implements MedicoDao {

	private static final String INSERT = "INSERT INTO clinica_medica.medicos"
			+ "(IdUsuario, Dni, Nombre, Apellido, Sexo, IdNacionalidad, FechaNacimiento, Direccion, IdJornada, "
			+ "IdLocalidad,IdEspecialidad, IdProvincia, CorreoElectronico, Telefono, Estado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_COUNT = "SELECT COUNT(*) FROM clinica_medica.medicos";
	private static final String CAMBIA_ESTADO = "UPDATE clinica_medica.medicos SET Estado = ? WHERE Dni = ?";
	private static final String UPDATE = "UPDATE clinica_medica.medicos SET Nombre = ?, Apellido = ?, Sexo = ?, IdNacionalidad = ?, "
			+ "FechaNacimiento = ?, Direccion = ?, IdLocalidad = ?, IdEspecialidad =?, IdProvincia = ?, CorreoElectronico = ?, Telefono = ?, Estado = ?, IdJornada = ? WHERE Dni = ?";
	private static final String READALL = "SELECT m.Id, m.IdUsuario, m.Dni, m.Nombre, m.Apellido, m.Sexo, m.IdNacionalidad, n.Nacionalidad, "
			+ "m.FechaNacimiento, m.Direccion, m.IdEspecialidad, e.Descripcion, m.IdLocalidad, l.Localidad, m.IdProvincia, pr.Provincia, m.CorreoElectronico, m.Telefono, m.Estado, "
			+ "m.IdJornada as IdJornada, j.Descripcion as DescripcionJor, j.Estado as EstadoJor, j.InicioLunes, j.FinLunes, j.InicioMartes, j.FinMartes, j.InicioMiercoles, j.FinMiercoles, j.InicioJueves, j.FinJueves, j.InicioViernes, j.FinViernes, j.InicioSabado, j.FinSabado, j.InicioDomingo, j.FinDomingo "			
			+ "FROM clinica_medica.medicos m "
			+ "INNER JOIN clinica_medica.especialidades e ON e.IdEspecialidad = m.IdEspecialidad "
			+ "INNER JOIN clinica_medica.nacionalidades n ON n.IdNacionalidad = m.IdNacionalidad "
			+ "INNER JOIN clinica_medica.provincias pr ON pr.IdProvincia = m.IdProvincia "
			+ "INNER JOIN clinica_medica.localidades l ON l.IdLocalidad = m.IdLocalidad "
			+ "INNER JOIN clinica_medica.jornadas j ON m.IdJornada = j.IdJornada";
	private static final String SEARCH = "SELECT m.Id, m.IdUsuario, m.Dni, m.Nombre, m.Apellido, m.Sexo, m.IdEspecialidad, e.Descripcion, m.IdNacionalidad, n.Nacionalidad, m.FechaNacimiento, m.Direccion, "
			+ "m.IdLocalidad, l.Localidad, m.IdProvincia, pr.Provincia, m.CorreoElectronico, m.Telefono, m.Estado, "
			+ "m.IdJornada as IdJornada, j.Descripcion as DescripcionJor, j.Estado as EstadoJor, j.InicioLunes, j.FinLunes, j.InicioMartes, j.FinMartes, j.InicioMiercoles, j.FinMiercoles, j.InicioJueves, j.FinJueves, j.InicioViernes, j.FinViernes, j.InicioSabado, j.FinSabado, j.InicioDomingo, j.FinDomingo "
			+ "FROM clinica_medica.medicos m "
			+ "INNER JOIN clinica_medica.especialidades e ON e.IdEspecialidad = m.IdEspecialidad "
			+ "INNER JOIN clinica_medica.nacionalidades n ON n.IdNacionalidad = m.IdNacionalidad "
			+ "INNER JOIN clinica_medica.provincias pr ON pr.IdProvincia = m.IdProvincia "
			+ "INNER JOIN clinica_medica.localidades l ON l.IdLocalidad = m.IdLocalidad "
			+ "INNER JOIN clinica_medica.jornadas j ON m.IdJornada = j.IdJornada "
			+ "WHERE m.Dni = ?";

	@Override
	public boolean insert(Medico medico) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(INSERT);
			statement.setInt(1, medico.getIdUsuario());
			statement.setInt(2, medico.getDni());
			statement.setString(3, medico.getNombre());
			statement.setString(4, medico.getApellido());
			statement.setString(5, medico.getSexo());					
			statement.setInt(6, medico.getNacionalidad().getIdNacionalidad());
			statement.setString(7, medico.getFechaNacimiento());
			statement.setString(8, medico.getDireccion());
			statement.setInt(9, medico.getJornada().getId());
			statement.setInt(10, medico.getLocalidad().getIdLocalidad());
			statement.setInt(11, medico.getEspecialidad().getIdEspecialidad());	
			statement.setInt(12, medico.getProvincia().getIdProvincia());
			statement.setString(13, medico.geteMail());
			statement.setString(14, medico.getTelefono());
			statement.setInt(15, 1);
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
	public int selectCount(Medico medico) {
		// TODO Auto-generated method stub
		PreparedStatement statement;
		ResultSet resultSet;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		int count = 0;
		try {
			String query = SELECT_COUNT;
			if (medico != null) {
				String where = " WHERE Dni = ?";
				statement = conexion.prepareStatement(query + where);
				statement.setInt(1, medico.getDni());
			} else
				statement = conexion.prepareStatement(query);
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
	public boolean update(Medico medico, boolean esBaja) {
		if (esBaja)
			return bajaMedico(medico);
		else
			return actualizaMedico(medico);
	}

	private boolean actualizaMedico(Medico medico) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean actualizado = false;
		try {
			statement = conexion.prepareStatement(UPDATE);
			statement.setString(1, medico.getNombre());
			statement.setString(2, medico.getApellido());
			statement.setString(3, medico.getSexo());
			statement.setInt(4, medico.getNacionalidad().getIdNacionalidad());
			statement.setString(5, medico.getFechaNacimiento());
			statement.setString(6, medico.getDireccion());
			statement.setInt(7, medico.getLocalidad().getIdLocalidad());
			statement.setInt(8, medico.getEspecialidad().getIdEspecialidad());	
			statement.setInt(9, medico.getProvincia().getIdProvincia());
			statement.setString(10, medico.geteMail());
			statement.setString(11, medico.getTelefono());
			statement.setInt(12, medico.getEstado());
			statement.setInt(13, medico.getJornada().getId());
			statement.setInt(14, medico.getDni());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				actualizado = true;
			}else {
				conexion.rollback();
			}
		} catch (SQLException e) {
			
		}
		return actualizado;
	}

	private boolean bajaMedico(Medico medico) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean estadoCambiado = false;
		try {
			statement = conexion.prepareStatement(CAMBIA_ESTADO);
			statement.setInt(1, 0);
			statement.setInt(2, medico.getDni());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				estadoCambiado = true;
			}
		} catch (SQLException e) {
		}
		return estadoCambiado;
	}

	@Override
	public ArrayList<Medico> readAll(int estado) {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Medico> medicos = new ArrayList<Medico>();
		Conexion conexion = Conexion.getConexion();
		try {
			String query = READALL;
			
			if (estado == 0)
				query += " WHERE m.estado = 0";
			else if (estado > 0)
				query += " WHERE m.estado = 1";			
			
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				medicos.add(getMedico(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medicos;
	}
	
	private Medico getMedico(ResultSet resultSet) throws SQLException {
		int idUsuario = resultSet.getInt("IdUsuario");
		int dni = resultSet.getInt("Dni");
		String nombre = resultSet.getString("Nombre");
		String apellido = resultSet.getString("Apellido");
		String sexo = resultSet.getString("Sexo");
		int idEspecialidad = resultSet.getInt("IdEspecialidad");
		String especialidad = resultSet.getString("Descripcion");
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
		Jornada jornada = getJornada(resultSet);
		
		return new Medico(idUsuario, dni, nombre, apellido, sexo, new Especialidad(idEspecialidad,especialidad),new Nacionalidad(idNacionalidad,nacionalidad), fechaNac, direccion, 
				new Localidad(idLocalidad, localidad, new Provincia(idProvincia, provincia)), new Provincia(idProvincia, provincia), correoElec, telefono, estado, jornada);
	}
	
	private Jornada getJornada(ResultSet resultSet) throws SQLException {
		int idJornada = resultSet.getInt("IdJornada");
		String descripcion = resultSet.getString("DescripcionJor");
		int inicioLunes = resultSet.getInt("InicioLunes");
		int finLunes = resultSet.getInt("FinLunes");
		int inicioMartes = resultSet.getInt("InicioMartes");
		int finMartes = resultSet.getInt("FinMartes");
		int inicioMiercoles = resultSet.getInt("InicioMiercoles");
		int finMiercoles = resultSet.getInt("FinMiercoles");
		int inicioJueves = resultSet.getInt("InicioJueves");
		int finJueves = resultSet.getInt("FinJueves");
		int inicioViernes = resultSet.getInt("InicioViernes");
		int finViernes = resultSet.getInt("FinViernes");
		int inicioSabado = resultSet.getInt("InicioSabado");
		int finSabado = resultSet.getInt("FinSabado");
		int inicioDomingo = resultSet.getInt("InicioDomingo");
		int finDomingo = resultSet.getInt("FinDomingo");
		return new Jornada(idJornada, descripcion, inicioLunes, finLunes, inicioMartes, finMartes, inicioMiercoles, finMiercoles, inicioJueves, finJueves, inicioViernes, finViernes,
				inicioSabado, finSabado, inicioDomingo, finDomingo);
	}
	
	@Override
	public Medico searchMedico(int dni) {
		PreparedStatement statement;
		ResultSet resultSet;
		Medico medico = null;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(SEARCH);
			statement.setInt(1, dni);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				medico = getMedico(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medico;
	}

}
