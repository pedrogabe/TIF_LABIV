package entidad;

public class VerificarDni {

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
}
