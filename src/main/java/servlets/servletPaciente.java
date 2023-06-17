package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import entidad.Paciente;
import negocio.PacienteNegocio;
import negocioImpl.PacienteNegocioImpl;

import negocio.ProvinciaNegocio;
import negocioImpl.ProvinciaNegocioImpl;
import entidad.Provincia;

import negocio.LocalidadNegocio;
import negocioImpl.LocalidadNegocioImpl;
import entidad.Localidad;
import entidad.Nacionalidad;
import negocio.NacionalidadNegocio;
import negocioImpl.*;

/**
 * Servlet implementation class Pacientes
 */
@WebServlet("/servletPaciente")
public class servletPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PacienteNegocio negPac = new PacienteNegocioImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public servletPaciente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if (op == null && request.getAttribute("op") != null ) 
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
				if(request.getParameter("dni")!=null)
					dni = Integer.parseInt(request.getParameter("dni"));
				else
					dni = Integer.parseInt(request.getAttribute("dni").toString());
			} catch (Exception e) {
				response.sendError(400);
				System.out.println("dni catch");
				return;
			}

			try {
				Paciente paciente = getPaciente(request, dni);
				request.setAttribute("paciente", paciente);
			} catch (Exception e) {
				response.sendError(500);
				return;
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("ABMPaciente.jsp");
		request.setAttribute("op", op);
		rd.forward(request, response);

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

		doGet(request, response);
		/*
		 * RequestDispatcher rd = request.getRequestDispatcher("ABMPaciente.jsp");
		 * rd.forward(request, response);
		 */
	}

	protected void postOp(HttpServletRequest request) {
		if (request.getParameter("btnGrabar") != null) {
			addPaciente(request);
			return;
		}

		if (request.getParameter("btnActualizar") != null) {
			updatePaciente(request);
			return;
		}

		if (request.getParameter("btnEliminar") != null) {
			deletePaciente(request);
			return;
		}

		request.setAttribute("error", "Error en solicitud"); // TODO -> Cambiar mensaje
	}

	protected void addPaciente(HttpServletRequest request) {
		Paciente paciente = new Paciente();
		request.setAttribute("op", "add");
		if (fillPaciente(request, paciente)) {
			if (true) {
				PacienteNegocio pacienteNeg = new PacienteNegocioImpl();
				if (!pacienteNeg.exists(paciente)) {
					pacienteNeg.insert(paciente);
					request.setAttribute("success", String.format("Se agregó el paciente (Dni %s)", paciente.getDni()));
				}
				else
					request.setAttribute("error", String.format("Ya existe el paciente con el (Dni %s)", paciente.getDni()));				
			}
		} else {
			request.setAttribute("error", "Datos"); // TODO -> cambiar mensaje
			request.setAttribute("paciente", paciente);
		}
	}

	protected void updatePaciente(HttpServletRequest request) {
		Paciente paciente = new Paciente();
		request.setAttribute("op", "edit");
		if (fillPaciente(request, paciente)) {
			if (true) { // TODO -> call negocio para guardar
				request.setAttribute("success", String.format("Se actualizó el paciente (Dni %s)", paciente.getDni()));
				request.setAttribute("maxIdPaciente", 1001);// TODO -> return max id
			}
		} else {
			request.setAttribute("error", "Datos"); // TODO -> cambiar mensaje
			request.setAttribute("paciente", paciente);
		}
	}

	@SuppressWarnings("unused")
	protected void deletePaciente(HttpServletRequest request) {
		int id;
		try {
			id = Integer.parseInt(request.getParameter("hfId"));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "400 delete");// TODO -> cambiar mensaje
			return;
		}

		if (true) { // TODO -> call negocio para eliminar
			request.setAttribute("success", String.format("Se eliminó el paciente %s", id));
			request.setAttribute("maxIdPaciente", 1001);// TODO -> return max id
		} else {
			request.setAttribute("error", "Datos"); // TODO -> cambiar mensaje
		}
	}

	protected Paciente getPaciente(HttpServletRequest request, int dni) {
		Object ps = request.getSession().getAttribute("pacientes");
		if (ps != null) {
			@SuppressWarnings("unchecked")
			List<Paciente> pacientes = (List<Paciente>) request.getSession().getAttribute("pacientes");
			for (Paciente paciente : pacientes) {
				if (paciente.getDni() == dni)
					return paciente;
			}

			request.setAttribute("error", "No se encontró el paciente solicitado");
			// TODO -> Excepcion personalizada?
		}
		// TODO -> call negocio
		return null;
	}

	protected boolean fillPaciente(HttpServletRequest request, Paciente paciente) {
		boolean valid = true;
		int id = 0, dni, estado = 1, idNac = 0;

		try {
			if(request.getParameter("txtDni")!=null)
				dni = Integer.parseInt(request.getParameter("txtDni"));
			else
				dni = Integer.parseInt(request.getParameter("txtDniHide"));// txtDniHide
		} catch (Exception e) {
			dni = 0;
			valid = false;
		}
		paciente.setDni(dni);
		request.setAttribute("dni", dni);

		String apellido, nombre, eMail, fechaNacimiento, localidad, nacionalidad, provincia, telefono, direccion, sexo;
		nombre = request.getParameter("txtNombre");
		if (nombre == null || nombre.equals("")) {
			valid = false;
			nombre = "";
		}
		paciente.setNombre(nombre);

		apellido = request.getParameter("txtApellido");
		if (apellido == null || apellido.equals("")) {
			valid = false;
			apellido = "";
		}
		paciente.setApellido(apellido);

		eMail = request.getParameter("txtEmail");
		if (eMail == null || eMail.equals("")) {
			valid = false;
			eMail = "";
		}
		paciente.seteMail(eMail);

		fechaNacimiento = request.getParameter("txtFecNacimiento");
		if (fechaNacimiento == null || fechaNacimiento.equals("")) {
			valid = false;
			fechaNacimiento = "";
		}
		paciente.setFechaNacimiento(fechaNacimiento);

		nacionalidad = request.getParameter("selNacionalidad");
		if (nacionalidad == null || nacionalidad.equals("")) {
			valid = false;
			nacionalidad = "";
		} else {
			idNac = Integer.parseInt(nacionalidad);
			paciente.setNacionalidad(new Nacionalidad(idNac, ""));
		}
		provincia = request.getParameter("selProvincia");
		if (provincia == null || provincia.equals("")) {
			valid = false;
			provincia = "";
		} else {
			int provinciaId = Integer.parseInt(provincia);

			Provincia prov = new Provincia();
			prov.setIdProvincia(provinciaId);
			paciente.setProvincia(prov);
		}

		localidad = request.getParameter("selLocalidad");
		if (localidad == null || localidad.equals("")) {
			valid = false;
			localidad = "";
		} else {
			int localidadId = Integer.parseInt(localidad);
			Localidad loca = new Localidad();
			loca.setIdLocalidad(localidadId);
			paciente.setLocalidad(loca);
		}

		telefono = request.getParameter("txtTelefono");
		if (telefono == null || telefono.equals("")) {
			valid = false;
			telefono = "";
		}
		paciente.setTelefono(telefono);

		direccion = request.getParameter("txtDireccion");
		if (direccion == null || direccion.equals("")) {
			valid = false;
			direccion = "";
		}
		paciente.setDireccion(direccion);

		sexo = request.getParameter("selSexo");
		if (sexo == null || sexo.equals("")) {
			valid = false;
			sexo = "";
		}
		paciente.setSexo(sexo);

		return valid;
	}

}
