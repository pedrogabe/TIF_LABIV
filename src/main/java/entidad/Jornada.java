package entidad;

public class Jornada {
	private int id;
	private String descripcion;
	private int inicioLunes;
	private int finLunes;
	private int inicioMartes;
	private int finMartes;
	private int inicioMiercoles;
	private int finMiercoles;
	private int inicioJueves;
	private int finJueves;
	private int inicioViernes;
	private int finViernes;
	private int inicioSabado;
	private int finSabado;
	private int inicioDomingo;
	private int finDomingo;
	
	public Jornada(int id) {
		this.id = id;
	}
	
	public Jornada(int id, String descripcion, int inicioLunes, int finLunes, int inicioMartes, int finMartes,
			int inicioMiercoles, int finMiercoles, int inicioJueves, int finJueves, int inicioViernes, int finViernes,
			int inicioSabado, int finSabado, int inicioDomingo, int finDomingo) {
		this.id = id;
		this.descripcion = descripcion;
		this.inicioLunes = inicioLunes;
		this.finLunes = finLunes;
		this.inicioMartes = inicioMartes;
		this.finMartes = finMartes;
		this.inicioMiercoles = inicioMiercoles;
		this.finMiercoles = finMiercoles;
		this.inicioJueves = inicioJueves;
		this.finJueves = finJueves;
		this.inicioViernes = inicioViernes;
		this.finViernes = finViernes;
		this.inicioSabado = inicioSabado;
		this.finSabado = finSabado;
		this.inicioDomingo = inicioDomingo;
		this.finDomingo = finDomingo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getInicioLunes() {
		return inicioLunes;
	}
	public void setInicioLunes(int inicioLunes) {
		this.inicioLunes = inicioLunes;
	}
	public int getFinLunes() {
		return finLunes;
	}
	public void setFinLunes(int finLunes) {
		this.finLunes = finLunes;
	}
	public int getInicioMartes() {
		return inicioMartes;
	}
	public void setInicioMartes(int inicioMartes) {
		this.inicioMartes = inicioMartes;
	}
	public int getFinMartes() {
		return finMartes;
	}
	public void setFinMartes(int finMartes) {
		this.finMartes = finMartes;
	}
	public int getInicioMiercoles() {
		return inicioMiercoles;
	}
	public void setInicioMiercoles(int inicioMiercoles) {
		this.inicioMiercoles = inicioMiercoles;
	}
	public int getFinMiercoles() {
		return finMiercoles;
	}
	public void setFinMiercoles(int finMiercoles) {
		this.finMiercoles = finMiercoles;
	}
	public int getInicioJueves() {
		return inicioJueves;
	}
	public void setInicioJueves(int inicioJueves) {
		this.inicioJueves = inicioJueves;
	}
	public int getFinJueves() {
		return finJueves;
	}
	public void setFinJueves(int finJueves) {
		this.finJueves = finJueves;
	}
	public int getInicioViernes() {
		return inicioViernes;
	}
	public void setInicioViernes(int inicioViernes) {
		this.inicioViernes = inicioViernes;
	}
	public int getFinViernes() {
		return finViernes;
	}
	public void setFinViernes(int finViernes) {
		this.finViernes = finViernes;
	}
	public int getInicioSabado() {
		return inicioSabado;
	}
	public void setInicioSabado(int inicioSabado) {
		this.inicioSabado = inicioSabado;
	}
	public int getFinSabado() {
		return finSabado;
	}
	public void setFinSabado(int finSabado) {
		this.finSabado = finSabado;
	}
	public int getInicioDomingo() {
		return inicioDomingo;
	}
	public void setInicioDomingo(int inicioDomingo) {
		this.inicioDomingo = inicioDomingo;
	}
	public int getFinDomingo() {
		return finDomingo;
	}
	public void setFinDomingo(int finDomingo) {
		this.finDomingo = finDomingo;
	}
	
	@Override
	public String toString() {
		return getDescripcion();
	}
}
