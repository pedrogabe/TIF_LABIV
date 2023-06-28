package entidad;

import java.sql.Date;
import excepcion.DniInvalido;
import excepcion.FechaInvalida;

public class Validaciones {

	public static void verificarDniInvalido(String dni) throws DniInvalido {

		if (!esNumerico(dni)) {
			throw new DniInvalido();
		}
	}

	private static boolean esNumerico(String dni) {

		try {
			Integer.parseInt(dni);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static void verificarFecha(Date fecha) throws FechaInvalida {
		Date hoy = Date.valueOf(java.time.LocalDate.now().toString());
		if(fecha.before(hoy))
			throw new FechaInvalida();
	}
}
