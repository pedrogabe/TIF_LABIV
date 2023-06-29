package negocioImpl;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.sql.Date;

import entidad.Jornada;
import entidad.Medico;
import entidad.Turno;
import dao.TurnoDao;
import daoImpl.TurnoDaoImpl;
import negocio.*;

public class TurnosNegocioImpl implements TurnosNegocio {
	private TurnoDao tdao = new TurnoDaoImpl();
	private MedicoNegocio mneg;
	private PacienteNegocio pneg; 

	public TurnosNegocioImpl() {
	}
	
	public TurnosNegocioImpl(MedicoNegocio mneg, PacienteNegocio pneg) {
		this.mneg = mneg;
		this.pneg = pneg;
	}
	
	@Override
	public boolean insert(Turno turno) {
		if(turnoValido(turno) && !turnoTomado(turno))
			return tdao.insert(turno);
		return false;
	}

	@Override
	public boolean delete(Turno turno) {
		return tdao.update(turno, true);
	}

	@Override
	public boolean update(Turno turno) {
		if(turnoValido(turno))
			return tdao.update(turno, false);
		return false;
	}

	@Override
	public ArrayList<Turno> readAll(int estado) {
		return tdao.readAll(estado);
	}

	@Override
	public boolean exists(Turno turno) {
		return tdao.searchTurno(turno.getIdTurno()) != null;
	}
	
	public boolean turnoValido(Turno turno) {
		if(!fechaYHoraValida(turno))
			return false;
		if(!mneg.exists(turno.getMedico())) 
			return false;
		if(!pneg.exists(turno.getPaciente()))
			return false;
		return true;
	}
	
	private boolean fechaYHoraValida(Turno turno) {
		if(turno.getHora() > 23 || turno.getHora() < 0)
			return false;
		if(!medicoAtiende(turno))
			return false;
		
		return true;
	}
	
	@SuppressWarnings("deprecation")
	private boolean medicoAtiende(Turno turno) {
		Date fecha = turno.getFechaReserva();
        return mneg.medicoAtiende(turno.getMedico(), diaSemana(fecha), turno.getHora());
	}
	
	private boolean turnoTomado(Turno turno) {
		int dniMed = turno.getMedico().getDni();
		int dniPac = turno.getPaciente().getDni();
		Date fecha = turno.getFechaReserva();
		
		ArrayList<Turno> tsDiaHorario = tdao.searchTurnosDiaHorario(fecha, turno.getHora());
		for(Turno tomado : tsDiaHorario) {
			if(dniMed == tomado.getMedico().getDni())
				return true;
			if(dniPac == tomado.getPaciente().getDni())
				return true;
		}
		
		return false;
	}
	
	public ArrayList<Turno> readTurnosMedico(Medico medico) {
		return tdao.searchTurnosMedico(medico.getDni());
	}
	
	@Override
	public ArrayList<Integer> turnosDisponiblesMedicoFecha(Medico medico, Date fecha){
		
		ArrayList<Turno> turnosMed = readTurnosMedico(medico);
		ArrayList<Integer> horasLibres = new ArrayList<Integer>();
		
		Jornada jornada = medico.getJornada();
        DayOfWeek dia = diaSemana(fecha);
        int inicio, fin;
		switch (dia) {
	        case MONDAY:
	        	inicio = jornada.getInicioLunes();
	        	fin = jornada.getFinLunes();
	        	break;
	        case TUESDAY:
	        	inicio = jornada.getInicioMartes();
	        	fin = jornada.getFinMartes();
	        	break;
	        case WEDNESDAY:
	        	inicio = jornada.getInicioMiercoles();
	        	fin = jornada.getFinMiercoles();
	        	break;
	        case THURSDAY:
	        	inicio = jornada.getInicioJueves();
	        	fin = jornada.getFinJueves();
	        	break;
	        case FRIDAY:
	        	inicio = jornada.getInicioViernes();
	        	fin = jornada.getFinViernes();
	        	break;
	        case SATURDAY:
	        	inicio = jornada.getInicioSabado();
	        	fin = jornada.getFinSabado();
	        	break;
	        case SUNDAY:
	        	inicio = jornada.getInicioDomingo();
	        	fin = jornada.getFinDomingo();
	        	break;
        	default:
	        		inicio = fin = 0;
		}
		
		for(int hora = inicio; hora<fin; hora++) {
			horasLibres.add(hora);
		}
		
		for(Turno turno : turnosMed) {
			if(turno.getFechaReserva().equals(fecha))
				horasLibres.remove((Integer)turno.getHora());
		}
		
		return horasLibres;
		
	}
	
	@SuppressWarnings("deprecation")
	private DayOfWeek diaSemana(Date fecha) {
		int d = fecha.getDay();
        return DayOfWeek.of(d == 0 ? 7 : d);
	}

}
