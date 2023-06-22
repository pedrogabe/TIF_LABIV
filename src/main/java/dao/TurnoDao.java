package dao;

import java.util.ArrayList;

import entidad.Turno;

public interface TurnoDao {
	public boolean insert(Turno turno);
	public boolean update(Turno turno, boolean esBaja);
	public ArrayList<Turno> readAll(int estado);
	public Turno searchTurno(int idTurno);
}