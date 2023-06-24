package negocio;

import java.util.ArrayList;
import entidad.Jornada;

public interface JornadaNegocio {
	public ArrayList<Jornada> readAll(int estado);
}
