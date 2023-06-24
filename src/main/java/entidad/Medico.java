package entidad;

import java.util.ArrayList;

public class Medico {
	
	private static final int DIAS = 7;
	private static final int HORAS = 24;	
	
	private int idUsuario;
	private int dni;
	private String nombre;
	private String apellido;
	private String sexo;
	private Especialidad especialidad;
	private Nacionalidad nacionalidad;
	private String fechaNacimiento;
	private String direccion;
	private Localidad localidad;
	private Provincia provincia;
	private String eMail;
	private String telefono;
	private Jornada jornada;
	private int estado;	
	
	
	public Medico(int idUsuario, int dni, String nombre, String apellido, String sexo,Especialidad especialidad, Nacionalidad nacionalidad, String fechaNacimiento,
			String direccion, Localidad localidad, Provincia provincia, String eMail, String telefono, int estado, Jornada jornada) {
		
		this.idUsuario = idUsuario;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.especialidad = especialidad;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.eMail = eMail;
		this.telefono = telefono;
		this.estado = estado;
		this.jornada = jornada;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}

	@Override
	public String toString() {
		return "Medico [idUsuario=" + idUsuario + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", sexo=" + sexo
				+ ", nacionalidad=" + nacionalidad + ", especialidad=" + especialidad + ", fechaNacimiento=" + fechaNacimiento + ", direccion=" + direccion
				+ ", localidad=" + localidad + ", provincia=" + provincia + ", eMail=" + eMail + ", telefono="
				+ telefono + ", estado=" + estado + ", jornada=" + jornada + "]";
	}

	
	
}
