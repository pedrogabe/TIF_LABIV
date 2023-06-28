package excepcion;

public class FechaInvalida extends Exception {
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "La fecha no puede ser anterior a hoy";
	}
}
