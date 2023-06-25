package negocioImpl;

import entidad.ReporteEspecialidad;
import negocio.RepEspecialidadNegocio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dao.RepEspecialidadDao;
import daoImpl.*;

public class RepEspecialidadNegocioImpl implements RepEspecialidadNegocio {

	@Override
	public ReporteEspecialidad searchReport(String anioMes) {
		RepEspecialidadDao repoEspDaoImpl = new RepEspecialidadDaoImpl();
		String sAnio = anioMes.substring(0, 4);
		String sMes = anioMes.substring(6);
		int mes = 1;
		try {
			mes = Integer.parseInt(sMes);
		} catch (Exception e) {

			return null;
		}

		return repoEspDaoImpl.searchReporteMes(mes);
	}

}
