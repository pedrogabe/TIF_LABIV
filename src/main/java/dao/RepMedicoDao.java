package dao;

import java.util.ArrayList;

import entidad.ReporteMedico;;

public interface RepMedicoDao {
	
	public ArrayList<ReporteMedico> searchReporteMes(int mes);
	
}
