package negocio;

import java.util.ArrayList;

import entidad.ReporteMedico;

public interface RepMedicoNegocio {
	public ArrayList<ReporteMedico> searchReport(String anioMes);
}
