package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidad.Paciente;

/**
 * Servlet implementation class Pacientes
 */
@WebServlet("/Pacientes")
public class ABMPacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		if(op.equals("add")) {
			//TODO -> return max id + 1
			request.setAttribute("maxIdPaciente", 1001);
		}
		else {
			int id;
			try{
				id = Integer.parseInt(request.getParameter("id"));
			}catch(Exception e) {
				response.sendError(400);
				return;					
			}
			
			try {
				request.setAttribute("paciente", getPaciente(id));
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
		Paciente paciente = new Paciente();
		if(fillPaciente(request, paciente)) {
			if(true) { //TODO -> call negocio para guardar
				 request.setAttribute("success", 
						 String.format("Se agregó el paciente %s (Dni %s)", paciente.getId(), paciente.getDni()));
				//TODO -> return max id + 1
				request.setAttribute("maxIdPaciente", 1001);
			}
		}
		else{
			request.setAttribute("error", "Datos"); //TODO -> cambiar mensaje
			request.setAttribute("paciente", paciente);			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("ABMLPaciente.jsp?1");
		rd.forward(request, response);
	}
	
	protected Paciente getPaciente(int id) {
		//TODO ->  call negocio
		return new Paciente(id, id, "Pepe", "Juarez", 2, "argentino", "2000-04-03", "Sarmiento 1234", "JJL", "Buenos Aires", "ppja@email.com", "1112345678", 1);
	}
	
	protected boolean fillPaciente(HttpServletRequest request, Paciente paciente) {
		boolean valid = true;
		int id = 0, dni, sexo, estado = 1;
		//TODO -> id
		try {
			dni = Integer.parseInt(request.getParameter("txtDni"));
		}catch(Exception e) {
			dni = 0;
			valid = false;
		}
		
		try {
			sexo = Integer.parseInt(request.getParameter("selSexo"));
		}catch(Exception e) {
			sexo = 0;
			valid = false;
		}
		
		String apellido, nombre, eMail, fechaNacimiento, localidad, nacionalidad, provincia, telefono, direccion;
		nombre = request.getParameter("txtNombre");
		if(nombre == null || nombre.equals("")) {
			valid = false;
			nombre = "";
		}
		apellido = request.getParameter("txtApellido");
		if(apellido == null || apellido.equals("")) {
			valid = false;
			apellido = "";
		}
		eMail = request.getParameter("txtEmail");
		if(eMail == null || eMail.equals("")) {
			valid = false;
			eMail = "";
		}
		fechaNacimiento = request.getParameter("txtFecNacimiento");
		if(fechaNacimiento == null || fechaNacimiento.equals("")) {
			valid = false;
			fechaNacimiento = "";
		}
		localidad = request.getParameter("txtLocalidad");
		if(localidad == null || localidad.equals("")) {
			valid = false;
			localidad = "";
		}
		nacionalidad = request.getParameter("txtNacionalidad");
		if(nacionalidad == null || nacionalidad.equals("")) {
			valid = false;
			nacionalidad = "";
		}
		provincia = request.getParameter("txtProvincia");
		if(provincia == null || provincia.equals("")) {
			valid = false;
			provincia = "";
		}
		telefono = request.getParameter("txtTelefono");
		if(telefono == null || telefono.equals("")) {
			valid = false;
			telefono = "";
		}
		direccion = request.getParameter("txtDireccion");
		if(direccion == null || direccion.equals("")) {
			valid = false;
			direccion = "";
		}
		
		paciente = new Paciente(0, dni, nombre, apellido, sexo, nacionalidad, fechaNacimiento, direccion, localidad, provincia, eMail, telefono, estado);
		
		return valid;
	}
	
	/*protected boolean validString(String param, String target) {
		target = param;
		if(target == null) {
			target = "";
			return false;
		}
		if(target.equals(""))
			return false;
		return true;
	}*/
	

}
