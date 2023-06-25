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

import entidad.*;
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

			NacionalidadNegocio negNac = new NacionalidadNegocioImpl();
			ArrayList<Nacionalidad> nacionalidades = negNac.readAll();

			ProvinciaNegocio negProv = new ProvinciaNegocioImpl();
			ArrayList<Provincia> provincias = negProv.readAll();

			LocalidadNegocio negLoc = new LocalidadNegocioImpl();
			ArrayList<Localidad> localidades = negLoc.readAll();
			
			EspecialidadNegocio negEspe = new EspecialidadNegocioImpl();
			ArrayList<Especialidad> especialidades = negEspe.readAll();

			JornadaNegocio negJor = new JornadaNegocioImpl();
			ArrayList<Jornada> jornadas = negJor.readAll(1);
			
			request.setAttribute("especialidades", especialidades);
			request.setAttribute("nacionalidades", nacionalidades);
			request.setAttribute("provincias", provincias);
			request.setAttribute("localidades", localidades);	
			request.setAttribute("jornadas", jornadas);
			
			if (op.equals("add")) {
				// Obsoleto
				//request.setAttribute("maxIdPaciente", 1001);

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
					Usuario usuario = getUsuario(medico.getIdUsuario());
					request.setAttribute("medico", medico);
					request.setAttribute("usuario", usuario);
				} catch (Exception e) {
					response.sendError(500);
					return;
				}
			}

			RequestDispatcher rd = request.getRequestDispatcher("ABMMedico.jsp");
			request.setAttribute("op", op);
			rd.forward(request, response);
		} else {
		    response.sendRedirect(request.getContextPath() + "/Error.jsp");
		}
	}

	private Usuario getUsuario(int idUsuario) {
		UsuarioNegocio usuarioNegImpl = new UsuarioNegocioImpl();
		
		return usuarioNegImpl.getUsuario(idUsuario);
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

		if (request.getAttribute("op") != null && (request.getAttribute("op").toString() == "edit" || request.getAttribute("op").toString() == "add")) {
			MedicoNegocio medicoNeg = new MedicoNegocioImpl();
			request.getSession().setAttribute("medicos", medicoNeg.readAll(1)); // Por default solo pacientes activos
			RequestDispatcher rd = request.getRequestDispatcher("ListarMedicos.jsp");
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
			updateMedico(request);
			return;
		}

		if (request.getParameter("btnEliminar") != null) {
			deleteMedico(request);
			return;
		}

		request.setAttribute("error", "Ocurrió un problema: La solicitud resulto inválida.");
	}

	private void addMedico(HttpServletRequest request) {
		Medico medico = fillMedico(request);
		Usuario usuario = fillUsuario(request);
		request.setAttribute("op", "add");

		if (medico != null && usuario != null) {
			MedicoNegocio medicoNegImpl = new MedicoNegocioImpl();
			if (!medicoNegImpl.exists(medico)) {
				if (medicoNegImpl.insert(medico, usuario)) {
					request.setAttribute("success",
							String.format("Se agregó el médico con el (Dni %s)", medico.getDni()));
				} else
					request.setAttribute("error",
							String.format("No agregó el médico con el (Dni %s)", medico.getDni()));
			} else
				request.setAttribute("error", String.format("Ya existe el médico con el (Dni %s) en la base de datos.", medico.getDni()));

		} else {
			request.setAttribute("error", "Hubo un problema en la validación de los datos");
			request.setAttribute("medico", medico);
			request.setAttribute("usuario", usuario);
		}
	}

	private Medico fillMedico(HttpServletRequest request) {
		boolean valid = true;
		Medico medico = null;
		int idUsuario = 0, dni, estado = 1, idNac = 0, idLoc = 0, idProv = 0, idEspecia = 0, idJor = 0;
		String apellido, nombre, eMail, fechaNacimiento, localidad, especialidad, nacionalidad, provincia, telefono, direccion, sexo, jornada;

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
		especialidad = request.getParameter("selEspecialidad");
		if (especialidad == null || especialidad.equals("")) {
			valid = false;
			especialidad = "";
		} else {
			idEspecia = Integer.parseInt(especialidad);
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
		
		jornada = request.getParameter("selJornada");
		if (jornada == null || jornada.equals("")) {
			valid = false;
			jornada = "";
		}
		else {
			idJor = Integer.parseInt(jornada);
		}

		if (valid) {
			medico = new Medico(idUsuario, dni, nombre, apellido, sexo,new Especialidad(idEspecia, ""), new Nacionalidad(idNac, ""), fechaNacimiento,
					direccion, new Localidad(idLoc, "", new Provincia(idProv, "")), new Provincia(idProv, ""), eMail,
					telefono, estado, new Jornada(idJor));
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

			request.setAttribute("error", "No se encontró el paciente solicitado en la base de datos.");
			// TODO -> Excepcion personalizada?
		}
		// TODO -> call negocio
		return null;
	}
	
	protected void updateMedico(HttpServletRequest request) {
		Medico medico = fillMedico(request);
		Usuario usuario = fillUsuario(request);
		request.setAttribute("op", "edit");
 
		MedicoNegocio medicoNeg = new MedicoNegocioImpl();
		if (medicoNeg.update(medico)) {
			request.setAttribute("success",
					String.format("Se actualizó el medico (Dni %s)", medico.getDni()));
		} else {
			request.setAttribute("error",
					String.format("No se actualizó el medico con el (Dni %s) en la base de datos.", medico.getDni()));
		}
		
		request.setAttribute("medico", medico);
		
	}
	
	protected void deleteMedico(HttpServletRequest request) {
		Medico medico = fillMedico(request);		
		request.setAttribute("op", "delete");
		
		MedicoNegocio medicoNeg = new MedicoNegocioImpl();
		if (medicoNeg.delete(medico)) {
			request.setAttribute("success",
					String.format("Se hizo la baja del medico (Dni %s) en la base de datos.", medico.getDni()));
		} else {
			request.setAttribute("error",
					String.format("No se hizo la baja del medico (Dni %s) en la base de datos.", medico.getDni()));
		}

		request.setAttribute("medico", medico);
		
	}
}
