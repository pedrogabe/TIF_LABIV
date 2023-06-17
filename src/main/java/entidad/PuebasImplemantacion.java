package entidad;

import java.util.List;

import dao.PacienteDao;
import daoImpl.PacienteDaoImpl;
import entidad.Paciente;

public class PuebasImplemantacion {

	public static void main(String[] args) {
		PacienteDao pacienteDao = new PacienteDaoImpl();

		/*
		 * if (pacienteDao.insert(crearPaciente()))
		 * System.out.println("Paciente ingresado"); else
		 * System.out.println("Paciente no ingresado");
		 */

		List<Paciente> pacientes = pacienteDao.readAll(-1);
		for (Paciente p : pacientes) {
			System.out.println(p.toString());
		}

	}

	private static Paciente crearPaciente() {
		Paciente paciente = new Paciente(0, null, null, null, null, null, null, null, null, null, null, 0);
		paciente.setDni(12345);
		paciente.setNombre("Pirulo");
		paciente.setApellido("Carolo");
		//paciente.setNacionalidad("Bulgaro");
		paciente.setFechaNacimiento("2000-10-10");
		paciente.setSexo("Maxculino");
		paciente.setDireccion("Su casa");
		//paciente.setLocalidad("Localidad");
		//paciente.setProvincia("Provincia");
		paciente.seteMail("pcarolo@correo.com");
		paciente.setTelefono("5555-2222");
		paciente.setEstado(1);

		return paciente;
	}

}
