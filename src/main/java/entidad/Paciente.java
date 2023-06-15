package entidad;

public class Paciente {	
	//TODO -> Agregar composición
	private int id;
	private int dni;
	private String nombre;
	private String apellido;
	private int sexo;
	private String nacionalidad;
	private String fechaNacimiento;
	private String direccion;
	private String localidad;
	private String provincia;
	private String eMail;
	private String telefono;	
	private int estado;
	
	
	public Paciente(int id, int dni, String nombre, String apellido, int sexo, String nacionalidad, String fechaNacimiento,
			String direccion, String localidad, String provincia, String eMail, String telefono, int estado) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.eMail = eMail;
		this.telefono = telefono;
		this.estado = estado;
	}	
	
	public Paciente() {super();}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getSexo() {
		return sexo;
	}
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
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
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
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
	
	@Override
	public String toString() {
		String est = this.estado == 0 ? "Inactivo" : "Activo"; 
		String sexo = this.sexo == 0 ? "No indica" : (this.sexo == 1 ? "Femenino" : "Masculino"); //TODO -> Sacar de otro lado 
		return "Pacciente "+ id + ": Dni=" + dni + ", Nombre=" + nombre + ", Apellido=" + apellido + ", Sexo=" + sexo
				+ ", Nacionalidad=" + nacionalidad + ", FechaNacimiento=" + fechaNacimiento + ", Direccion=" + direccion
				+ ", Localidad=" + localidad + ", Provincia=" + provincia + ", eMail=" + eMail + ", Telefono=" + telefono + ", Estado=" + est;
	}	

}
