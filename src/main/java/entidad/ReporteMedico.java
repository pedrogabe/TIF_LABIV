package entidad;

public class ReporteMedico {
	
	private int total_por_mes;
	private Medico medico;
	private int total_por_medico;
	private String procentaje_por_medico;
	
	public ReporteMedico () {		
	}
	
	public int getTotal_por_mes() {
		return total_por_mes;
	}
	public void setTotal_por_mes(int total_por_mes) {
		this.total_por_mes = total_por_mes;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public int getTotal_por_medico() {
		return total_por_medico;
	}
	public void setTotal_por_medico(int total_por_medico) {
		this.total_por_medico = total_por_medico;
	}
	
	public String getProcentaje_por_medico() {
		return procentaje_por_medico;
	}

	public void setProcentaje_por_medico(String procentaje_por_medico) {
		this.procentaje_por_medico = procentaje_por_medico;
	}

	@Override
	public String toString() {
		return "ReporteMedico [total_por_mes=" + total_por_mes + ", medico=" + medico + ", total_por_medico="
				+ total_por_medico + ", procentaje_por_medico=" + procentaje_por_medico + "]";
	}
}
