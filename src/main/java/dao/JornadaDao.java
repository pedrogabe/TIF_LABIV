package dao;

import java.util.ArrayList;
import entidad.Jornada;

public interface JornadaDao {
	public ArrayList<Jornada> readAll(int estado);
}
