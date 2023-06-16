package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import entidad.*;
import negocio.PacienteNegocio;
import negocioImpl.PacienteNegocioImpl;
import dao.LocalidadDao;
import dao.ProvinciaDao;
import dao.NacionalidadDao;//TODO -> Cambiar por negocio?
import daoImpl.LocalidadDaoImpl;
import daoImpl.ProvinciaDaoImpl;
import daoImpl.NacionalidadDaoImpl;//TODO -> Cambiar por negocio?


/**
 * Servlet implementation class Pacientes
 */
@WebServlet("/Pacientes")
public class ABMPacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PacienteNegocio negPac = new PacienteNegocioImpl();
	private static LocalidadDao daoLoc = new LocalidadDaoImpl();
	private static ProvinciaDao daoProv = new ProvinciaDaoImpl();
	private static NacionalidadDao daoNac = new NacionalidadDaoImpl();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMPacientes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");

		if(op==null || !(op.equals("add") || op.equals("edit") || op.equals("delete")) ) {
			response.sendError(400);
			return;
		}
		
		request.setAttribute("nacionalidades", daoNac.readAll());
		request.setAttribute("provincias", daoProv.readAll());
		request.setAttribute("localidades", daoLoc.readAll());
		
		if(!op.equals("add")) {
			int id;
			try{
				id = Integer.parseInt(request.getParameter("id"));
			}catch(Exception e) {
				response.sendError(400);
				return;					
			}
			
			try {
				request.setAttribute("paciente", getPaciente(request, id));
			}catch(Exception e) {
				response.sendError(500);
				return;
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("ABMLPaciente.jsp");
		request.setAttribute("op", op);
		rd.forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.removeAttribute("error");
		request.removeAttribute("success");
		
		postOp(request);
		
		RequestDispatcher rd = request.getRequestDispatcher("ABMLPaciente.jsp?1");
		rd.forward(request, response);
	}
	
	protected void postOp(HttpServletRequest request) {
		if(request.getParameter("btnGrabar")!=null) {
			addPaciente(request);
			return;
		}
		
		if(request.getParameter("btnActualizar")!=null) {
			updatePaciente(request);
			return;
		}
		
		if(request.getParameter("btnEliminar")!=null) {
			deletePaciente(request);
			return;
		}
		
		request.setAttribute("error", "Error en solicitud"); //TODO -> Cambiar mensaje
	}
	
	protected void addPaciente(HttpServletRequest request) {
		Paciente paciente = new Paciente();
		if(fillPaciente(request, paciente)) {
			if(negPac.insert(paciente)) {
				 request.setAttribute("success", 
						 String.format("Se agregó el paciente (Dni %s)", paciente.getDni()));
			}
			else {
				request.setAttribute("error", "Hubo un problema al grabar en la base"); //TODO -> cambiar mensaje
				request.setAttribute("paciente", paciente);	
			}
		}
		else{
			request.setAttribute("error", "Datos"); //TODO -> cambiar mensaje
			request.setAttribute("paciente", paciente);			
		}
	}
	
	protected void updatePaciente(HttpServletRequest request) {
		Paciente paciente = new Paciente();
		if(fillPaciente(request, paciente)) {
			if(true) { //TODO -> call negocio para guardar
				 request.setAttribute("success", 
						 String.format("Se actualizó el paciente (Dni %s)", paciente.getDni()));
				request.setAttribute("maxIdPaciente", 1001);//TODO -> return max id
			}
			else {
				request.setAttribute("error", "Hubo un problema al grabar en la base"); //TODO -> cambiar mensaje
				request.setAttribute("paciente", paciente);	
			}
		}
		else{
			request.setAttribute("error", "Datos"); //TODO -> cambiar mensaje
			request.setAttribute("paciente", paciente);			
		}
	}
	
	protected void deletePaciente(HttpServletRequest request) {
		int id;
		try {
			id = Integer.parseInt(request.getParameter("hfId"));
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "400 delete");//TODO -> cambiar mensaje
			return;			
		}
		
		Paciente paciente = new Paciente();
		if(negPac.delete(paciente)) {
			request.setAttribute("success", 
					 String.format("Se eliminó el paciente %s", id));
		}else {
			request.setAttribute("error", "Datos"); //TODO -> cambiar mensaje
		}
	}
	
	protected Paciente getPaciente(HttpServletRequest request, int dni) {
		Object ps = request.getSession().getAttribute("pacientes");
		if(ps != null) {
			List<Paciente> pacientes = (List<Paciente>)request.getSession().getAttribute("pacientes");
			for(Paciente paciente : pacientes) {
				if(paciente.getDni() == dni)
					return paciente;
			}
			
			request.setAttribute("error", "No se encontró el paciente solicitado");
			//TODO -> Excepcion personalizada?
		}
		//TODO ->  call negocio
		return null;
	}
	
	protected boolean fillPaciente(HttpServletRequest request, Paciente paciente) {
		boolean valid = true;
		int dni, estado = 1;
		
		try {
			dni = Integer.parseInt(request.getParameter("txtDni"));
		}catch(Exception e) {
			dni = 0;
			valid = false;
		}
		paciente.setDni(dni);
		
		
		String apellido, nombre, eMail, fechaNacimiento, telefono, direccion, sexo;
		nombre = request.getParameter("txtNombre");
		if(nombre == null || nombre.equals("")) {
			valid = false;
			nombre = "";
		}
		paciente.setNombre(nombre);
		
		apellido = request.getParameter("txtApellido");
		if(apellido == null || apellido.equals("")) {
			valid = false;
			apellido = "";
		}
		paciente.setApellido(apellido);
		
		eMail = request.getParameter("txtEmail");
		if(eMail == null || eMail.equals("")) {
			valid = false;
			eMail = "";
		}
		paciente.seteMail(eMail);
		
		fechaNacimiento = request.getParameter("txtFecNacimiento");
		if(fechaNacimiento == null || fechaNacimiento.equals("")) {
			valid = false;
			fechaNacimiento = "";
		}
		paciente.setFechaNacimiento(fechaNacimiento);
		
		
		telefono = request.getParameter("txtTelefono");
		if(telefono == null || telefono.equals("")) {
			valid = false;
			telefono = "";
		}
		paciente.setTelefono(telefono);
		
		direccion = request.getParameter("txtDireccion");
		if(direccion == null || direccion.equals("")) {
			valid = false;
			direccion = "";
		}
		paciente.setDireccion(direccion);
		
		sexo = request.getParameter("selSexo");
		if(sexo == null || sexo.equals("")) {
			valid = false;
			sexo = "";
		}
		paciente.setSexo(sexo);
		
		try {
			Nacionalidad nacionalidad = new Nacionalidad(Integer.parseInt(request.getParameter("selNacionalidad")), null);
			paciente.setNacionalidad(nacionalidad);
		}catch(Exception e) {
			valid=false;
		}
		
		try {
			Provincia provincia = new Provincia(Integer.parseInt(request.getParameter("selProvincia")), null);
			paciente.setProvincia(provincia);
			
			Localidad localidad = new Localidad(Integer.parseInt(request.getParameter("selLocalidad")), null, provincia);
			paciente.setLocalidad(localidad);
		}catch(Exception e) {
			valid=false;
		}
		
		
		
		return valid;
	}
	

}
