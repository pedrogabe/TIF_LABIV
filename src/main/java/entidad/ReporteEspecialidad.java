package entidad;

import java.util.ArrayList;

public class ReporteEspecialidad {
	
	private ArrayList<String> columnas;
	private ArrayList<String> valores;
	
	public ReporteEspecialidad() {
		columnas = new ArrayList<String>();
		valores = new ArrayList<String>();
	}

	public ArrayList<String> getColumnas() {
		return columnas;
	}

	public void setColumnas(ArrayList<String> columnas) {
		this.columnas = columnas;
	}
	
	public void addColumna(String columna) {
		this.columnas.add(columna);
	}

	public int getTotalColumnas() {
		return this.columnas.size();
	}	
	
	public ArrayList<String> getValores() {
		return valores;
	}

	public void setValores(ArrayList<String> valores) {
		this.valores = valores;
	}

	public void addValor(String valor) {
		this.valores.add(valor);
	}
}
