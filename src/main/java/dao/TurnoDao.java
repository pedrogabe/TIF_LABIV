package dao;

import java.sql.Date;
import java.util.ArrayList;

import entidad.Turno;

public interface TurnoDao {
	public boolean insert(Turno turno);
	public boolean update(Turno turno, boolean esBaja);
	public ArrayList<Turno> readAll(int estado);
	public Turno searchTurno(int idTurno);
	public ArrayList<Turno> searchTurnosDiaHorario(Date fecha, int hora);
	public ArrayList<Turno> searchTurnosMedico(int dniMedico);
}