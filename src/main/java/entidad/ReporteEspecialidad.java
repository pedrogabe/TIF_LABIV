package entidad;

import java.util.ArrayList;

public class ReporteEspecialidad {

	private ArrayList<String> columnas;
	private ArrayList<String> valores;
	private ArrayList<String> columnasDet;
	private ArrayList<String> valoresDet;

	public ReporteEspecialidad() {
		columnas = new ArrayList<String>();
		valores = new ArrayList<String>();
		columnasDet = new ArrayList<String>();
		valoresDet = new ArrayList<String>();
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

	public int getTotalFilasPorColumnas() {
		if (this.columnas.size() > 0)
			return this.valores.size() / this.columnas.size();
		else
			return 0;
	}

	public ArrayList<String> getValores() {
		return valores;
	}

	public String getValorIndex(int index) {
		return valores.get(index);
	}

	public void setValores(ArrayList<String> valores) {
		this.valores = valores;
	}

	public void addValor(String valor) {
		this.valores.add(valor);
	}

	public ArrayList<String> getColumnasDet() {
		return columnasDet;
	}

	public void setColumnasDet(ArrayList<String> columnas) {
		this.columnasDet = columnas;
	}

	public void addColumnaDet(String columna) {
		this.columnasDet.add(columna);
	}

	public int getTotalColumnasDet() {
		return this.columnasDet.size();
	}

	public int getTotalFilasPorColumnasDet() {
		if (this.columnasDet.size() > 0)
			return this.valoresDet.size() / this.columnasDet.size();
		else
			return 0;
	}

	public ArrayList<String> getValoresDet() {
		return valoresDet;
	}

	public String getValorDetIndex(int index) {
		return valoresDet.get(index);
	}

	public void setValoresDet(ArrayList<String> valores) {
		this.valoresDet = valores;
	}

	public void addValorDet(String valor) {
		this.valoresDet.add(valor);
	}

}
