package negocioImpl;


import entidad.ReporteMedico;
import negocio.RepMedicoNegocio;

import java.util.ArrayList;

import dao.RepMedicoDao;
import daoImpl.*;

public class RepMedicoNegocioImpl implements RepMedicoNegocio{

	@Override
	public ArrayList<ReporteMedico> searchReport(String anioMes) {
		RepMedicoDao repoMedDaoImpl = new RepMedicoDaoImpl();
		String sMes = anioMes.substring(6);
		int mes = 1;
		try {
			mes = Integer.parseInt(sMes);
		} catch (Exception e) {

			return null;
		}

		return repoMedDaoImpl.searchReporteMes(mes);
	}

}
