package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Medico;
import entidad.Provincia;
import entidad.Usuario;
import negocio.*;
import negocioImpl.*;

/**
 * Servlet implementation class ServletMedico
 */
@WebServlet("/ServletMedico")
public class ServletMedico extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletMedico() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("Usuario") != null) {
			String op = request.getParameter("op");
			if (op == null && request.getAttribute("op") != null)
				op = request.getAttribute("op").toString();
			if (op == null || !(op.equals("add") || op.equals("edit") || op.equals("delete"))) {
				response.sendError(400);
				System.out.println("op catch");
				return;
			}

			// TODO-> llenar nacionalidades,provincias,localidades
			NacionalidadNegocio negNac = new NacionalidadNegocioImpl();
			ArrayList<Nacionalidad> nacionalidades = negNac.readAll();

			ProvinciaNegocio negProv = new ProvinciaNegocioImpl();
			ArrayList<Provincia> provincias = negProv.readAll();

			LocalidadNegocio negLoc = new LocalidadNegocioImpl();
			ArrayList<Localidad> localidades = negLoc.readAll();
			request.setAttribute("nacionalidades", nacionalidades);
			request.setAttribute("provincias", provincias);
			request.setAttribute("localidades", localidades);
			if (op.equals("add")) {
				// TODO -> return max id
				request.setAttribute("maxIdPaciente", 1001);

			} else {
				int dni;
				try {
					if (request.getParameter("dni") != null)
						dni = Integer.parseInt(request.getParameter("dni"));
					else
						dni = Integer.parseInt(request.getAttribute("dni").toString());
				} catch (Exception e) {
					response.sendError(400);
					System.out.println("dni catch");
					return;
				}

				try {
					Medico medico = getMedico(request, dni);
					request.setAttribute("medico", medico);
				} catch (Exception e) {
					response.sendError(500);
					return;
				}
			}

			RequestDispatcher rd = request.getRequestDispatcher("ABMMedico.jsp");
			request.setAttribute("op", op);
			rd.forward(request, response);
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<font color=red size18>No tiene autorizacion, debe ingresar con usuario!<br>");
			out.println("<a href=Login.jsp>Ir al Login!</a>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.removeAttribute("error");
		request.removeAttribute("success");

		postOp(request);

		if (request.getAttribute("op") != null && request.getAttribute("op").toString() == "edit") {
			PacienteNegocio negocio = new PacienteNegocioImpl();
			request.getSession().setAttribute("pacientes", negocio.readAll(1)); // Por default solo pacientes activos
			RequestDispatcher rd = request.getRequestDispatcher("ListarPacientes.jsp");
			rd.forward(request, response);
		}
		doGet(request, response);
	}

	protected void postOp(HttpServletRequest request) {
		if (request.getParameter("btnGrabar") != null) {
			addMedico(request);
			return;
		}

		if (request.getParameter("btnActualizar") != null) {
			addMedico(request);
			return;
		}

		if (request.getParameter("btnEliminar") != null) {
			addMedico(request);
			return;
		}

		request.setAttribute("error", "Error en solicitud"); // TODO -> Cambiar mensaje
	}

	private void addMedico(HttpServletRequest request) {
		Medico medico = fillMedico(request);
		Usuario usuario = fillUsuario(request);
		request.setAttribute("op", "add");

		if (medico != null && usuario != null) {
			MedicoNegocio medicoNegImpl = new MedicoNegocioImpl();
			UsuarioNegocio usuarioNegImpl = new UsuarioNegocioImpl();
			if (!medicoNegImpl.exists(medico) && usuarioNegImpl.insert(usuario)) {
				medicoNegImpl.insert(medico);
				request.setAttribute("success", String.format("Se agregó el médico con el (Dni %s)", medico.getDni()));
			} else
				request.setAttribute("error", String.format("Ya existe el médico con el (Dni %s)", medico.getDni()));

		} else {
			request.setAttribute("error", "Datos"); // TODO -> cambiar mensaje
			request.setAttribute("medico", medico);
			request.setAttribute("usuario", usuario);
		}
	}

	private Medico fillMedico(HttpServletRequest request) {
		boolean valid = true;
		Medico medico = null;
		int idUsuario = 0, dni, estado = 1, idNac = 0, idLoc = 0, idProv = 0;
		String apellido, nombre, eMail, fechaNacimiento, localidad, nacionalidad, provincia, telefono, direccion, sexo;

		try {
			if (request.getParameter("txtDni") != null)
				dni = Integer.parseInt(request.getParameter("txtDni"));
			else
				dni = Integer.parseInt(request.getParameter("txtDniHide"));// txtDniHide
		} catch (Exception e) {
			dni = 0;
			valid = false;
		}
		request.setAttribute("dni", dni);

		nombre = request.getParameter("txtNombre");
		if (nombre == null || nombre.equals("")) {
			valid = false;
			nombre = "";
		}

		apellido = request.getParameter("txtApellido");
		if (apellido == null || apellido.equals("")) {
			valid = false;
			apellido = "";
		}

		eMail = request.getParameter("txtEmail");
		if (eMail == null || eMail.equals("")) {
			valid = false;
			eMail = "";
		}

		fechaNacimiento = request.getParameter("txtFecNacimiento");
		if (fechaNacimiento == null || fechaNacimiento.equals("")) {
			valid = false;
			fechaNacimiento = "";
		}

		nacionalidad = request.getParameter("selNacionalidad");
		if (nacionalidad == null || nacionalidad.equals("")) {
			valid = false;
			nacionalidad = "";
		} else {
			idNac = Integer.parseInt(nacionalidad);
		}
		provincia = request.getParameter("selProvincia");
		if (provincia == null || provincia.equals("")) {
			valid = false;
			provincia = "";
		} else {
			idProv = Integer.parseInt(provincia);
		}

		localidad = request.getParameter("selLocalidad");
		if (localidad == null || localidad.equals("")) {
			valid = false;
			localidad = "";
		} else {
			idLoc = Integer.parseInt(localidad);
		}

		telefono = request.getParameter("txtTelefono");
		if (telefono == null || telefono.equals("")) {
			valid = false;
			telefono = "";
		}

		direccion = request.getParameter("txtDireccion");
		if (direccion == null || direccion.equals("")) {
			valid = false;
			direccion = "";
		}

		sexo = request.getParameter("selSexo");
		if (sexo == null || sexo.equals("")) {
			valid = false;
			sexo = "";
		}

		if (valid) {
			medico = new Medico(idUsuario, dni, nombre, apellido, sexo, new Nacionalidad(idNac, ""), fechaNacimiento,
					direccion, new Localidad(idLoc, "", new Provincia(idProv, "")), new Provincia(idProv, ""), eMail,
					telefono, estado);
			//
		}
		return medico;
	}

	private Usuario fillUsuario(HttpServletRequest request) {
		Usuario usuario = null;
		boolean valid = true;

		String userLogin, password;
		userLogin = request.getParameter("txtUserLogin");
		if (userLogin == null || userLogin.equals("")) {
			valid = false;
			userLogin = "";
		}

		password = request.getParameter("txtPassword");
		if (userLogin == null || userLogin.equals("")) {
			valid = false;
			userLogin = "";
		}
		if (valid) {
			usuario = new Usuario(0, userLogin, password, 2);
		}
		return usuario;
	}

	protected Medico getMedico(HttpServletRequest request, int dni) {
		Object md = request.getSession().getAttribute("medicos");
		if (md != null) {
			@SuppressWarnings("unchecked")
			List<Medico> medicos = (List<Medico>) request.getSession().getAttribute("medicos");
			for (Medico medico : medicos) {
				if (medico.getDni() == dni)
					return medico;
			}

			request.setAttribute("error", "No se encontró el paciente solicitado");
			// TODO -> Excepcion personalizada?
		}
		// TODO -> call negocio
		return null;
	}

}
